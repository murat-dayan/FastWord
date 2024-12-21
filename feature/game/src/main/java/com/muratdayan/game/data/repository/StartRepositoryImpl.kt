package com.muratdayan.game.data.repository

import android.util.Log
import com.muratdayan.common.AppError
import com.muratdayan.common.DataError
import com.muratdayan.common.Result
import com.muratdayan.game.domain.model.QuestionModel
import com.muratdayan.game.domain.model.RoomModel
import com.muratdayan.game.domain.repository.StartRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.rpc
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

    override fun getRandomQuestion(): Flow<Result<QuestionModel, AppError>> = flow {

        try {
            val response = supabaseClient
                .postgrest
                .rpc("get_random_question")

            Log.e("StartRepositoryImpl","getRandomQuestion: $response")

            val question = response.decodeSingleOrNull<QuestionModel>()

            if (question != null) {
                emit(Result.Success(question))
            } else {
                Log.e("StartRepositoryImpl", "getRandomQuestion: Question not found")
                emit(Result.Error(DataError.Remote.ServerError))
            }
        }catch (e:Exception){
            Log.e("StartRepositoryImpl","getRandomQuestion: ${e.message}")
            emit(Result.Error(DataError.Remote.ServerError))
        }

    }


}