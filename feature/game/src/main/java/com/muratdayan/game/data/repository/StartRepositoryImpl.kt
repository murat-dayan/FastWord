package com.muratdayan.game.data.repository

import android.util.Log
import com.muratdayan.common.AppError
import com.muratdayan.common.DataError
import com.muratdayan.common.Result
import com.muratdayan.game.domain.model.QuestionModel
import com.muratdayan.game.domain.model.RoomModel
import com.muratdayan.game.domain.model.RoomRoundModel
import com.muratdayan.game.domain.repository.StartRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StartRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : StartRepository{
    override fun getRoom(roomId: String): Flow<Result<RoomModel, AppError>> = flow {
        try {
            val response = supabaseClient
                .from("rooms")
                .select {
                    filter {
                        eq("id", roomId)
                    }
                }.decodeSingleOrNull<RoomModel>()

            if (response != null) {
                emit(Result.Success(response))
            } else {
                Log.e("StartRepositoryImpl","getRoom: Room not found")
                emit(Result.Error(DataError.Remote.ServerError))
            }
        }catch (e:Exception){
            Log.e("StartRepositoryImpl","getRoom: ${e.message}")
            emit(Result.Error(DataError.Remote.ServerError))

        }
    }

    override fun getQuestion(questionId: String): Flow<Result<QuestionModel, AppError>> = flow {

        try {
            val response = supabaseClient
                .from("questions")
                .select (Columns.raw("*")){
                    select()
                    filter {
                        eq("id",questionId)
                    }
                }

            Log.e("StartRepositoryImpl","getQuestion: $response")

            val question = response.decodeSingleOrNull<QuestionModel>()

            if (question != null) {
                emit(Result.Success(question))
            } else {
                Log.e("StartRepositoryImpl", "getQuestion: Question not found")
                emit(Result.Error(DataError.Remote.ServerError))
            }
        }catch (e:Exception){
            Log.e("StartRepositoryImpl","getQuestion: ${e.message}")
            emit(Result.Error(DataError.Remote.ServerError))
        }

    }

    override fun getRoomRound(roomRoundId: String): Flow<Result<RoomRoundModel, AppError>> = flow{
        try {
            val response = supabaseClient
                .from("room_rounds")
                .select (Columns.raw("*")){
                    filter {
                        eq("id",roomRoundId)
                    }
                }

            val decodedResponse = response.decodeSingleOrNull<RoomRoundModel>()

            if (decodedResponse != null) {
                emit(Result.Success(decodedResponse))
            } else {
                Log.e("StartRepositoryImpl","getRoomRound: RoomRound not found")
                emit(Result.Error(DataError.Remote.ServerError))
            }

        }catch (e:Exception){
            Log.e("StartRepositoryImpl","getRoomRound: ${e.message}")
            emit(Result.Error(DataError.Remote.ServerError))
        }
    }


}