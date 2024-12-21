package com.muratdayan.friends.presentation

object FriendsContract {

    data class UiState(
        val userStats: com.muratdayan.domain.model.UserStatsModel? = null,
        val friends: List<com.muratdayan.domain.model.FriendsDataModel>? = null,
        val pendingFriends: List<com.muratdayan.domain.model.RequestedFriendsModel> = emptyList(),
        val isLoading: Boolean = false,
    )

    sealed interface UiAction{
        data object InviteFriends:UiAction
        data object GetUserStats: UiAction
        data object GetPendingFriends: UiAction
        data object GetFriends: UiAction
        data object GoToShop: UiAction
        data object GoToProfile: UiAction
        data class UpdateFriendStatus(val senderId: String,val status: String): UiAction
        data object NavigateToBack: UiAction
    }

    sealed class UiEffect(){
        data object NavigateToShopScreen: UiEffect()
        data object NavigateToProfileScreen: UiEffect()
        data object NavigateToBack: UiEffect()

    }
}