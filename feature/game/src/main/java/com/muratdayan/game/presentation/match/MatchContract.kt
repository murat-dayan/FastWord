package com.muratdayan.game.presentation.match

import com.muratdayan.domain.model.UserDataModel
import com.muratdayan.game.domain.model.RoomModel

object MatchContract {

    data class UiState(
        val userInfo: UserDataModel? = null,
        val room: RoomModel? = null,
        val isLoading: Boolean = false,
    )

    sealed interface UiAction{
        data object GetUserInfo: UiAction
        data class FindOrCreateRoom(val userId:String): UiAction
    }

    sealed class UiEffect(){
        data object NavigateToPlayScreen: UiEffect()
    }
}