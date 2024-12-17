package com.muratdayan.game.presentation.main

import com.muratdayan.domain.model.UserStatsModel

object MainScreenContract {

    data class UiState(
        val userStats: com.muratdayan.domain.model.UserStatsModel? = null,
        val friends: List<com.muratdayan.domain.model.FriendsDataModel>? = null,
        val isLoading: Boolean = false,
    )

    sealed interface UiAction{
        data object PlayNow:UiAction
        data object GetUserStats: UiAction
        data object GetFriends: UiAction
        data object GoToShop: UiAction
        data object GoToSettings: UiAction
        data object GoToFriends: UiAction
        data object GoToLeaderBoard: UiAction
        data class GoToProfile(val userId: String): UiAction
    }

    sealed class UiEffect(){
        data object NavigateToPlayScreen: UiEffect()
        data object NavigateToShopScreen: UiEffect()
        data object NavigateToSettingsScreen: UiEffect()
        data object NavigateToFriendsScreen: UiEffect()
        data object NavigateToLeaderBoardScreen: UiEffect()
        data class NavigateToProfileScreen(val userId:String): UiEffect()

    }
}