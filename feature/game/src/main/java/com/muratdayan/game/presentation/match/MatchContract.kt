package com.muratdayan.game.presentation.match

import com.muratdayan.domain.model.UserDataModel
import com.muratdayan.game.domain.model.RoomModel

object MatchContract {

    data class UiState(
        val userInfo: UserDataModel? = null,
        val isWaiting: Boolean = false,
        val room: RoomModel? = null,
        val isLoading: Boolean = false,
    )

    sealed interface UiAction{
        data object GetUserInfo: UiAction
        data class FindOrCreateRoom(val userId:String): UiAction
        data class GoToStartScreen(val opponentUserId:String):UiAction
        data class GoToBack(val roomId:String):UiAction
    }

    sealed class UiEffect(){
        data class NavigateToStartScreen(val opponentUserId:String): UiEffect()
        data object NavigateToBack: UiEffect()
    }
}