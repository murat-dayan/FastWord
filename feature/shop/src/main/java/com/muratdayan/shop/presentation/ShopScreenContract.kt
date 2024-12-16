package com.muratdayan.shop.presentation

object ShopScreenContract {

    data class UiState(
        val userStats: com.muratdayan.domain.model.UserStatsModel? = null,
        val isLoading: Boolean = false,
    )

    sealed interface UiAction{
        data object GetUserStats: UiAction
    }

    sealed class UiEffect(){
    }
}