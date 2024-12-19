package com.muratdayan.friends.data.repository

import android.util.Log
import com.muratdayan.common.AppError
import com.muratdayan.common.DataError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.FriendsDataModel
import com.muratdayan.domain.model.RequestedFriendsModel
import com.muratdayan.friends.domain.repository.FriendsRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Locale.filter
import javax.inject.Inject

class FriendsRepositoryImpl @Inject constructor(
    private val supabaseClient:SupabaseClient
) : FriendsRepository {

    override fun getPendingFriends(): Flow<Result<List<RequestedFriendsModel>, AppError>> = flow {
        try {
            val user = supabaseClient.auth.currentUserOrNull()

            if (user == null){
                emit(Result.Error(DataError.Remote.Unauthorized))
                return@flow
            }else {
                val response = supabaseClient
                    .from("friends")
                    .select(
                        Columns.raw(
                            """
                        friend_id,
                        user:users!user_id(id,user_name),
                        status
                    """.trimIndent()
                        )
                    ) {
                        filter {
                            eq("friend_id", user.id)
                            eq("status", "pending")
                        }
                    }

                val decodeResponse = response.decodeList<RequestedFriendsModel>()

                if (decodeResponse.isEmpty()){
                    emit(Result.Error(DataError.Remote.ServerError))
                }else{
                    emit(Result.Success(decodeResponse))
                }
            }
        }catch (e:Exception){
            Log.e("FriendsRepositoryImpl","getPendingFriends: ${e.message}")
            emit(Result.Error(DataError.Remote.ServerError))
        }
    }
}