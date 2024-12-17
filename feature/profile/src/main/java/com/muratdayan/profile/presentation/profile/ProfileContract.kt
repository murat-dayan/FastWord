package com.muratdayan.profile.presentation.profile

import com.muratdayan.domain.model.UserStatsModel

object ProfileContract {

    data class UiState(
        val userStats: UserStatsModel? = null,
        val isLoading: Boolean = false,
    )

    sealed interface UiAction{
        data object GetUserStats: UiAction
    }

    sealed class UiEffect(){
    }
}