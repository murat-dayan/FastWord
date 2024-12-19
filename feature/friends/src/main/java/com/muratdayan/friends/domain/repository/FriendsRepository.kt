package com.muratdayan.friends.domain.repository

import com.muratdayan.common.AppError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.FriendsDataModel
import com.muratdayan.domain.model.RequestedFriendsModel
import kotlinx.coroutines.flow.Flow

interface FriendsRepository {

    fun getPendingFriends(): Flow<Result<List<RequestedFriendsModel>,AppError>>
}