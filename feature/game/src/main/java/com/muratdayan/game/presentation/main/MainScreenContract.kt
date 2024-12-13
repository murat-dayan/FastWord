package com.muratdayan.game.presentation.main

import com.muratdayan.game.domain.model.UserStatsModel

object MainScreenContract {

    data class UiState(
        val userStats: UserStatsModel? = null,
        val isLoading: Boolean = false,
    )

    sealed interface UiAction{
        data object PlayNow:UiAction
        data object GetUserStats: UiAction
    }

    sealed class UiEffect(){
        data object NavigateToPlayScreen: UiEffect()
    }
}