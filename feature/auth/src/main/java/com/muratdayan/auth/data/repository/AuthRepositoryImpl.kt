package com.muratdayan.auth.data.repository

import android.util.Log
import com.muratdayan.auth.domain.repository.AuthRepository
import com.muratdayan.auth.utils.generateRandomName
import com.muratdayan.common.AppError
import com.muratdayan.common.DataError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.UserDataModel
import com.muratdayan.domain.model.UserStatsModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Facebook
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.UUID
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : AuthRepository{

    override fun guestSignIn(): Flow<Result<Boolean, AppError>> = flow {

        try {
            var randomName = generateRandomName()

            while (isUserNameTaken(randomName)){
                randomName = generateRandomName()
            }

            val userMetadata = buildJsonObject {
                put("displayName", randomName)
            }

            supabaseClient.auth.signInAnonymously(
                data = userMetadata
            )

            supabaseClient.auth.refreshCurrentSession()

            val user = supabaseClient.auth.currentUserOrNull()

            if (user != null){

                val userData = UserDataModel(
                    id = user.id,
                    user_name = randomName
                )
                supabaseClient.from("users").insert(userData)
                saveUserStats(user.id)
                emit(Result.Success(true))

            }else{
                Log.e("AuthRepositoryImpl", "guestSignIn: User is null", )
                emit(Result.Error(DataError.Remote.ServerError))
            }
        }catch (e:Exception){
            Log.e("AuthRepositoryImpl", "guestSignIn: ", e)
            emit(
                when (e) {
                    is SocketTimeoutException -> Result.Error(DataError.Remote.RequestTimeout)
                    is UnknownHostException -> Result.Error(DataError.Remote.NoInternet)
                    is IOException -> Result.Error(DataError.Local.DiskFull)
                    is SecurityException -> Result.Error(DataError.Local.PermissionDenied)
                    is SerializationException -> Result.Error(DataError.Remote.SerializationError)
                    else -> Result.Error(DataError.Remote.Unknown)
                }
            )
        }

    }

    override fun facebookSignIn(): Flow<Result<Boolean, AppError>> = flow {
        try {
            val user = supabaseClient.auth.currentUserOrNull()
            if (user != null){
                val displayName = user.userMetadata?.get("name")?.toString() ?: "Anonym"

                val userData = UserDataModel(
                    id = user.id,
                    user_name = displayName
                )
                supabaseClient.from("users").insert(userData)
                emit(Result.Success(true))
            }else{
                Log.e("AuthRepositoryImpl", "facebookSignIn: User is null", )
                emit(Result.Error(DataError.Remote.ServerError))
            }
        }catch (e:Exception){
            Log.e("AuthRepositoryImpl", "facebookSignIn: ", e)
            emit(
                when (e) {
                    is SocketTimeoutException -> Result.Error(DataError.Remote.RequestTimeout)
                    is UnknownHostException -> Result.Error(DataError.Remote.NoInternet)
                    is IOException -> Result.Error(DataError.Local.DiskFull)
                    is SecurityException -> Result.Error(DataError.Local.PermissionDenied)
                    is SerializationException -> Result.Error(DataError.Remote.SerializationError)
                    else -> Result.Error(DataError.Remote.Unknown)
                }
            )
        }
    }

    override fun getFacebookSignInUrl(): String {
        return supabaseClient.auth.getOAuthUrl(
            Facebook,
            redirectUrl = "https://oytgzsqqshuvhpezvcbp.supabase.co/auth/v1/callback"
        )
    }


    private suspend fun isUserNameTaken(userName: String): Boolean {
        return try {
            val response = supabaseClient
                .from("users")
                .select {
                    filter {
                        eq("user_name", userName)
                    }
                }.decodeSingleOrNull<UserDataModel>()
            response != null
        }catch (e:Exception){
            Log.e("AuthRepositoryImpl", "isUserNameTaken: ", e)
            true
        }

    }

    private suspend fun saveUserStats(userId: String){
        val userStats = UserStatsModel(userId)
        supabaseClient.from("user_stats").insert(userStats)

    }


}