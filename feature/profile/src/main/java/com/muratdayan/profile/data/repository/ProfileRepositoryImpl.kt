package com.muratdayan.profile.data.repository

import android.util.Log
import com.muratdayan.common.AppError
import com.muratdayan.common.DataError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.FriendsDataModel
import com.muratdayan.profile.domain.repository.ProfileRepository
import com.muratdayan.profile.presentation.profile.util.UserType
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : ProfileRepository{
    override fun checkUserType(userId: String): Flow<Result<UserType, AppError>> = flow {
        try {
            val user = supabaseClient.auth.currentUserOrNull()
            Log.d("UserStatsDomainRepositoryImpl", "updateEnergy: $user")

            if (user == null){
                emit(Result.Error(DataError.Remote.Unauthorized))
                return@flow
            }else {
                if (userId == user.id){
                    emit(Result.Success(UserType.CURRENT))
                    return@flow
                }else{
                    val response = supabaseClient
                        .from("friends")
                        .select(
                            Columns.raw("""
                        friend_id,
                        user:users!friend_id(id,user_name)
                    """.trimIndent())){
                            filter {
                                eq("user_id",user.id)
                                eq("status","accepted")
                            }
                        }

                    val decodeResponse = response.decodeList<FriendsDataModel>()

                    if (decodeResponse.any { friend->
                        friend.user.id == userId
                    }){
                        emit(Result.Success(UserType.FRIEND))
                    }else{
                        emit(Result.Success(UserType.OTHER))
                    }
                }
            }


        }catch (e:Exception){
            emit(Result.Error(DataError.Remote.ServerError))
        }

    }


}