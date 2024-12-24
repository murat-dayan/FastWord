package com.muratdayan.game.data.repository

import android.util.Log
import com.muratdayan.common.AppError
import com.muratdayan.common.DataError
import com.muratdayan.common.Result
import com.muratdayan.game.domain.model.MatchResult
import com.muratdayan.game.domain.model.RoomModel
import com.muratdayan.game.domain.repository.MatchRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.filter.FilterOperator
import io.github.jan.supabase.postgrest.rpc
import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresChangeFlow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : MatchRepository {
    override fun findOrCreateRoom(userId: String): Flow<Result<MatchResult, AppError>> = flow {
        try {
            val findResult = findRoom(userId)

            if (findResult != null) {
                emit(Result.Success(MatchResult.RoomFound(findResult)))
                return@flow
            }

            val createResult = createRoom(userId)

            if (createResult != null) {
                emit(Result.Success(MatchResult.RoomCreated(createResult)))
                return@flow
            } else {
                emit(Result.Error(DataError.Remote.ServerError))
            }

        } catch (e: Exception) {
            Log.d("MatchRepositoryImpl", "findOrCreateRoom: ${e.message}")
            emit(Result.Error(DataError.Remote.ServerError))
        }
    }

    private suspend fun findRoom(userId: String): RoomModel? {
        return try {
            val waitingRoomResponse = supabaseClient
                .from("rooms")
                .select(Columns.raw("*")) {
                    filter {
                        eq("status", "waiting")
                    }
                }
            Log.d("MatchRepositoryImpl", "findRoom: ${waitingRoomResponse.data}")

            val waitingRooms = waitingRoomResponse.decodeList<RoomModel>()

            if (waitingRooms.isNotEmpty()) {

                if (waitingRooms[0].player_one_id == userId || waitingRooms[0].player_two_id == userId) {
                    return null
                }

                val updatedRoomResponse = supabaseClient
                    .from("rooms")
                    .update({
                        set("player_two_id", userId)
                        set("status", "playing")
                        set("is_room_ready", true)
                    }) {
                        select()
                        filter {
                            waitingRooms[0].id?.let { eq("id", it) }
                        }
                    }.decodeSingleOrNull<RoomModel>()
                Log.d("MatchRepositoryImpl", "findRoom - updatedRoomResponse: $updatedRoomResponse")
                updatedRoomResponse
            } else {
                null
            }
        } catch (e: Exception) {
            Log.d("MatchRepositoryImpl", "findRoom: ${e.message}")
            null
        }
    }

    private suspend fun createRoom(userId: String): RoomModel? {
        return try {

            val questionId = getRandomQuestion()

            val roomModel = RoomModel(
                player_one_id = userId,
                player_two_id = null,
                status = "waiting",
                question_id = questionId
            )

            val newRoomResponse = supabaseClient
                .from("rooms")
                .insert(roomModel) {
                    select()
                }

            Log.d("MatchRepositoryImpl", "createRoom: $newRoomResponse")

            val newRoom = newRoomResponse.decodeList<RoomModel>()

            if (newRoom.isNotEmpty()) newRoom[0] else null
        } catch (e: Exception) {
            Log.d("MatchRepositoryImpl", "createRoom: ${e.message}")
            null
        }
    }


    private  suspend fun getRandomQuestion(): String{
        val response = supabaseClient
            .postgrest
            .rpc("get_random_question_id")

        val questionId = response.decodeAs<String>()

        return questionId
    }

    override fun startRealtimeRoomListener(roomId: String): Flow<RoomModel> = channelFlow {
        val channel = supabaseClient.channel("rooms_channel_$roomId") // Add roomId to make channel unique

        try {
            channel.subscribe()

            val roomChangeFlow = channel.postgresChangeFlow<PostgresAction.Update>(
                schema = "public",
            ) {
                table = "rooms"
                filter("id", FilterOperator.EQ, roomId)
            }

            val initialRoom = supabaseClient
                .from("rooms")
                .select {
                    select()
                    filter {
                        eq("id", roomId)
                    }
                }.decodeSingleOrNull<RoomModel>()

            initialRoom?.let {
                send(it)
            }

            roomChangeFlow.onEach { action ->
                action.record.let { jsonObject ->
                    val room = Json.decodeFromJsonElement<RoomModel>(jsonObject)
                    Log.d("MatchRepositoryImpl", "Room updated: $room")
                    send(room)
                }
            }.launchIn(this)

            awaitClose {
                launch { // launch a new coroutine for unsubscribe
                    try {
                        channel.unsubscribe()
                    } catch (e: Exception) {
                        Log.e("MatchRepositoryImpl", "Error unsubscribing: ${e.message}")
                    }
                }
            }


        }catch (e:Exception){
            Log.d("MatchRepositoryImpl", "startRealtimeRoomListener: ${e.message}")
        }


    }

    override fun deleteRoomUseCase(roomId: String): Flow<Result<Unit, AppError>> = flow {
        try {
            supabaseClient
                .from("rooms")
                .delete{
                    filter {
                        eq("id",roomId)
                    }
                }

            emit(Result.Success(Unit))

        }catch (e:Exception){
            Log.d("MatchRepositoryImpl", "deleteRoomUseCase: ${e.message}")
            emit(Result.Error(DataError.Remote.ServerError))
        }
    }

    override suspend fun setStatusPlaying(roomId: String){
        try {
            supabaseClient
                .from("rooms")
                .update({
                    set("status","playing")
                }){
                    filter {
                        eq("id",roomId)
                    }
                }
        }catch (e:Exception){
            Log.d("MatchRepositoryImpl", "setStatusPlaying: ${e.message}")
        }

    }


}