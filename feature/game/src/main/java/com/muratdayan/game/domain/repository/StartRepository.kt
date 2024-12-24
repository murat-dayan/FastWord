package com.muratdayan.game.domain.repository

import com.muratdayan.common.AppError
import com.muratdayan.common.Result
import com.muratdayan.game.domain.model.QuestionModel
import com.muratdayan.game.domain.model.RoomModel
import kotlinx.coroutines.flow.Flow

interface StartRepository {

    fun getRoom(roomId:String): Flow<Result<RoomModel,AppError>>

    fun getQuestion(questionId:String): Flow<Result<QuestionModel,AppError>>
}