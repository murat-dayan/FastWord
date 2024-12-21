package com.muratdayan.profile.domain.repository

import com.muratdayan.common.AppError
import com.muratdayan.common.Result
import com.muratdayan.profile.presentation.profile.util.UserType
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun checkUserType(userId:String): Flow<Result<UserType,AppError>>

    fun sendFriendRequest(friendId:String): Flow<Result<Unit,AppError>>

    fun getAvatars(folderName:String): Flow<Result<List<String>,AppError>>

    fun updateProfileImage(imageUri:String): Flow<Result<Unit,AppError>>
}