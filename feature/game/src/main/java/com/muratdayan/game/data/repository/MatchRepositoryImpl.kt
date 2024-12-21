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
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.filter.FilterOperator
import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresChangeFlow
import io.github.jan.supabase.realtime.postgresSingleDataFlow
import io.github.jan.supabase.realtime.realtime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Locale.filter
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
            val roomModel = RoomModel(
                player_one_id = userId,
                player_two_id = null,
                status = "waiting"
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

    override fun startRealtimeRoomListener(roomId: String): Flow<RoomModel> = flow {
        val channel = supabaseClient.channel("rooms_channel")

        try {
            channel.subscribe()

            val roomFlow: Flow<RoomModel> = channel.postgresSingleDataFlow(
                schema = "public",
                table = "rooms",
                primaryKey = RoomModel::id
            ) {
                eq("id", roomId)
            }

            try {
                roomFlow.collect {
                    emit(it)
                }
            } finally {
                channel.unsubscribe()
            }
        } catch (e: Exception) {
            channel.unsubscribe()
            throw e
        }
    }.catch { cause ->
        // Flow seviyesinde hata yakalama
        println("Error in room listener: ${cause.message}")
        throw cause
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


}