package com.muratdayan.leaderboard.domain.repository

import com.muratdayan.common.AppError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.UserDataModel
import kotlinx.coroutines.flow.Flow

interface LeaderBoardRepository {

    fun getEveryone(): Flow<Result<List<UserDataModel>,AppError>>
}