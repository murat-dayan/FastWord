package com.muratdayan.game.data.repository

import android.util.Log
import com.muratdayan.common.AppError
import com.muratdayan.common.DataError
import com.muratdayan.common.Result
import com.muratdayan.game.domain.model.RoomModel
import com.muratdayan.game.domain.repository.StartRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
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


}