package com.muratdayan.game.domain.repository

import com.muratdayan.common.AppError
import com.muratdayan.common.Result
import com.muratdayan.game.domain.model.MatchResult
import com.muratdayan.game.domain.model.RoomModel
import kotlinx.coroutines.flow.Flow

interface MatchRepository {

    fun findOrCreateRoom(userId:String): Flow<Result<MatchResult,AppError>>

    fun startRealtimeRoomListener(roomId:String): Flow<RoomModel>

    fun deleteRoomUseCase(roomId:String): Flow<Result<Unit,AppError>>
}