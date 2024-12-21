package com.muratdayan.profile.data.repository

import android.util.Log
import com.muratdayan.common.AppError
import com.muratdayan.common.DataError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.AcceptedFriendsModel
import com.muratdayan.domain.model.FriendsDataModel
import com.muratdayan.domain.model.FriendsTableModel
import com.muratdayan.domain.model.RequestedFriendsModel
import com.muratdayan.profile.domain.repository.ProfileRepository
import com.muratdayan.profile.presentation.profile.util.UserType
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : ProfileRepository {

    override fun checkUserType(userId: String): Flow<Result<UserType, AppError>> = flow {
        try {
            val currentUser = supabaseClient.auth.currentUserOrNull()
            Log.d("ProfileRepositoryImpl", "updateEnergy: $currentUser")

            if (currentUser == null) {
                emit(Result.Error(DataError.Remote.Unauthorized))
                return@flow
            }

            if (userId == currentUser.id) {
                emit(Result.Success(UserType.CURRENT))
                return@flow
            }

            val requestedFriendResponse = supabaseClient
                .from("friends")
                .select(
                    Columns.raw(
                        """
                    friend_id,
                    user:users!friend_id(id,user_name,avatar_uri),
                    status
                    """.trimIndent()
                    )
                ) {
                    filter {
                        eq("user_id", currentUser.id)
                        eq("friend_id", userId)
                    }
                }.decodeSingleOrNull<RequestedFriendsModel>()

            // İstek alınan arkadaşlık durumu
            val acceptedFriendResponse = supabaseClient
                .from("friends")
                .select(
                    Columns.raw(
                        """
                    user_id,
                    user:users!user_id(id,user_name,avatar_uri),
                    status
                    """.trimIndent()
                    )
                ) {
                    filter {
                        eq("friend_id", currentUser.id)
                        eq("user_id", userId)
                    }
                }.decodeSingleOrNull<AcceptedFriendsModel>()


            val userType = when {
                requestedFriendResponse?.status == "accepted" -> UserType.FRIEND
                acceptedFriendResponse?.status == "accepted" -> UserType.FRIEND
                requestedFriendResponse?.status == "pending" -> UserType.PENDING
                acceptedFriendResponse?.status == "pending" -> UserType.PENDING
                else -> UserType.OTHER
            }

            emit(Result.Success(userType))


        } catch (e: Exception) {
            Log.e("ProfileRepositoryImpl", "updateEnergy: ${e.message}")
            emit(Result.Error(DataError.Remote.ServerError))
        }

    }

    override fun sendFriendRequest(friendId: String): Flow<Result<Unit, AppError>> = flow {
        try {
            val user = supabaseClient.auth.currentUserOrNull()
            Log.d("ProfileRepositoryImpl", "updateEnergy: $user")

            if (user == null) {
                emit(Result.Error(DataError.Remote.Unauthorized))
                return@flow
            } else {

                val friendsTableModel = FriendsTableModel(
                    user_id = user.id,
                    friend_id = friendId,
                    status = "pending"
                )
                supabaseClient
                    .from("friends")
                    .insert(friendsTableModel)

                emit(Result.Success(Unit))
            }
        } catch (e: Exception) {
            emit(Result.Error(DataError.Remote.ServerError))
        }
    }

    override fun getAvatars(folderName: String): Flow<Result<List<String>, AppError>> = flow {
        try {
            val supabaseStorage = supabaseClient.storage
            val bucket = supabaseStorage.from("avatars")

            val list = bucket.list(folderName)
            Log.d("ProfileRepositoryImpl", "getAvatars: $list")

            val photoUrlList = list.map {
                bucket.publicUrl("$folderName/${it.name}")
            }
            Log.d("ProfileRepositoryImpl", "getAvatars: $photoUrlList")

            emit(Result.Success(photoUrlList))
        } catch (e: Exception) {
            Log.d("ProfileRepositoryImpl", "getAvatars: $e")
            emit(Result.Error(DataError.Remote.ServerError))
        }
    }

    override fun updateProfileImage(imageUri: String): Flow<Result<Unit, AppError>> = flow {
        try {
            val user = supabaseClient.auth.currentUserOrNull()

            if (user == null) {
                emit(Result.Error(DataError.Remote.Unauthorized))
                return@flow
            } else {
                supabaseClient
                    .from("users")
                    .update({
                        set("avatar_uri", imageUri)
                    }){
                        filter {
                            eq("id",user.id)
                        }
                    }

                emit(Result.Success(Unit))
            }
        }catch (e:Exception){
            Log.d("ProfileRepositoryImpl", "updateProfileImage: $e")
            emit(Result.Error(DataError.Remote.ServerError))
        }
    }


}