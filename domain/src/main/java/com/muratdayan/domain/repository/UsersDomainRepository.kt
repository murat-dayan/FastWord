package com.muratdayan.domain.repository

import com.muratdayan.common.AppError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.UserStatsModel
import kotlinx.coroutines.flow.Flow

interface UsersDomainRepository {
    fun getUserStats(): Flow<Result<UserStatsModel, AppError>>
}