package com.muratdayan.game.domain.repository

import com.muratdayan.common.AppError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.FriendsDataModel
import com.muratdayan.domain.model.UserStatsModel
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    fun getUserStats(): Flow<Result<UserStatsModel, AppError>>

}