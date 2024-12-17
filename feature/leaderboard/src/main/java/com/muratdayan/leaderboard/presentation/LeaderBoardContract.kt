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
        data object GoToProfile: UiAction
    }

    sealed class UiEffect(){
        data object NavigateToProfileScreen: UiEffect()
    }
}