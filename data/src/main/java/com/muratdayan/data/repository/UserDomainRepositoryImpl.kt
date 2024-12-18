package com.muratdayan.data.repository

import android.util.Log
import com.muratdayan.common.AppError
import com.muratdayan.common.DataError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.UserDataModel
import com.muratdayan.domain.model.UserStatsModel
import com.muratdayan.domain.repository.UserDomainRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDomainRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : UserDomainRepository{
    override fun getUser(): Flow<Result<UserDataModel, AppError>> = flow {
        try {
            val user = supabaseClient.auth.currentUserOrNull()
            Log.d("UserDomainRepositoryImpl", "updateToken: $user")

            if (user == null){
                emit(Result.Error(DataError.Remote.Unauthorized))
                return@flow
            }else {
                val response = supabaseClient
                    .from("users")
                    .select {
                        filter {
                            eq("id",user.id)
                        }
                    }.decodeSingleOrNull<UserDataModel>()

                if (response == null){
                    emit(Result.Error(DataError.Remote.ServerError))
                }else{
                    emit(Result.Success(response))
                }
            }
        }catch (e:Exception){
            Log.e("UserDomainRepositoryImpl",e.message.toString())
            emit(Result.Error(DataError.Remote.ServerError))

        }
    }


}