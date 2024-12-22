package com.muratdayan.game.presentation.play

import com.muratdayan.domain.model.UserDataModel
import com.muratdayan.game.domain.model.QuestionModel
import com.muratdayan.game.domain.model.RoomModel

object PlayScreenContract {

    data class UiState(
        val isLoading: Boolean = false,
        val userInfo: UserDataModel? = null,
        val question: QuestionModel? = null,
        val opponentInfo: UserDataModel? = null,
        val room: RoomModel? = null
    )

    sealed interface UiAction{
        data object GetUserInfo: UiAction
        data class GetOpponentInfo(val opponentId: String): UiAction
    }


    sealed class UiEffect(){
        data object NavigateToPlayScreen: UiEffect()
    }
}