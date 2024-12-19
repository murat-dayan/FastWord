package com.muratdayan.leaderboard.presentation

object LeaderBoardContract {

    data class UiState(
        val friends: List<com.muratdayan.domain.model.FriendsDataModel>? = null,
        val everyoneList: List<com.muratdayan.domain.model.UserDataModel>? = null,
        val isLoading: Boolean = false,
    )

    sealed interface UiAction{
        data object GetFriends: UiAction
        data object GetEveryone: UiAction
        data class GoToProfile(val userId: String): UiAction
    }

    sealed class UiEffect(){
        data class NavigateToProfileScreen(val userId:String): UiEffect()
    }
}