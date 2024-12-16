package com.muratdayan.domain.repository

import com.muratdayan.common.AppError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.UserStatsModel
import kotlinx.coroutines.flow.Flow

interface UserStatsDomainRepository {
    fun getUserStats(): Flow<Result<UserStatsModel, AppError>>

    fun updateToken(tokenNewValue:Int) : Flow<Result<Unit, AppError>>

    fun updateEnergy(energyNewValue:Int) : Flow<Result<Unit, AppError>>

    fun updateEmerald(emeraldNewValue:Int) : Flow<Result<Unit, AppError>>

}