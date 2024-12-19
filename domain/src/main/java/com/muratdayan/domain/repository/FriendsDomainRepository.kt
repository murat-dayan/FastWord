package com.muratdayan.domain.repository

import com.muratdayan.common.AppError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.FriendsDataModel
import com.muratdayan.domain.model.FriendsViewModel
import kotlinx.coroutines.flow.Flow

interface FriendsDomainRepository {
    fun getFriends() : Flow<Result<List<FriendsViewModel>, AppError>>

    fun updateFriendStatus(statusValue:String, senderId:String): Flow<Result<Unit, AppError>>
}