package com.muratdayan.game.presentation.main

import com.muratdayan.domain.model.UserStatsModel

object MainScreenContract {

    data class UiState(
        val userStats: com.muratdayan.domain.model.UserStatsModel? = null,
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