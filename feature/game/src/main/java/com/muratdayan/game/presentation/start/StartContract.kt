package com.muratdayan.game.presentation.start

import com.muratdayan.domain.model.UserDataModel
import com.muratdayan.game.domain.model.QuestionModel
import com.muratdayan.game.domain.model.RoomModel
import com.muratdayan.game.domain.model.RoomRoundModel

object StartContract {

    data class UiState(
        val isLoading: Boolean = false,
        val userInfo: UserDataModel? = null,
        val question: QuestionModel? = null,
        val opponentInfo: UserDataModel? = null,
        val room: RoomModel? = null,
        val roomRound: RoomRoundModel? = null,
    )

    sealed interface UiAction{
        data object GetUserInfo: UiAction
        data class GetRoom(val roomId: String): UiAction
        data class GetOpponentInfo(val opponentId: String): UiAction
    }


    sealed class UiEffect(){
        data object NavigateToPlayScreen: UiEffect()
    }
}