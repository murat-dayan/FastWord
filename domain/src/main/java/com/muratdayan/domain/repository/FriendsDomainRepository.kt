package com.muratdayan.domain.repository

import com.muratdayan.common.AppError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.FriendsDataModel
import kotlinx.coroutines.flow.Flow

interface FriendsDomainRepository {
    fun getFriends() : Flow<Result<List<FriendsDataModel>, AppError>>

    fun updateFriendStatus(statusValue:String, friendId:String): Flow<Result<Unit, AppError>>
}