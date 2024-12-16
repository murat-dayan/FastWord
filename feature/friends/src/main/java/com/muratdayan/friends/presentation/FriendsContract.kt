package com.muratdayan.friends.presentation

object FriendsContract {

    data class UiState(
        val userStats: com.muratdayan.domain.model.UserStatsModel? = null,
        val friends: List<com.muratdayan.domain.model.FriendsDataModel>? = null,
        val isLoading: Boolean = false,
    )

    sealed interface UiAction{
        data object InviteFriends:UiAction
        data object GetUserStats: UiAction
        data object GetFriends: UiAction
        data object GoToShop: UiAction
        data object GoToProfile: UiAction
    }

    sealed class UiEffect(){
        data object NavigateToShopScreen: UiEffect()
        data object NavigateToProfileScreen: UiEffect()

    }
}