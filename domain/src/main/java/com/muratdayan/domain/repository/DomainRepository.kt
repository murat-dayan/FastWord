package com.muratdayan.domain.repository

import com.muratdayan.common.AppError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.FriendsDataModel
import kotlinx.coroutines.flow.Flow

interface DomainRepository {
    fun getFriends() : Flow<Result<List<FriendsDataModel>, AppError>>
}