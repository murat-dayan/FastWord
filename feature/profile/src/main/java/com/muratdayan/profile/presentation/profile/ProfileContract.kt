package com.muratdayan.profile.presentation.profile

import com.muratdayan.domain.model.UserStatsModel
import com.muratdayan.profile.presentation.profile.util.UserType

object ProfileContract {

    data class UiState(
        val userStats: UserStatsModel? = null,
        val userType: UserType? = null,
        val isLoading: Boolean = false,
    )

    sealed interface UiAction{
        data object GetUserStats: UiAction
        data class CheckUserType(val userId:String): UiAction
        data class SendFriendRequest(val friendId:String): UiAction
    }

    sealed class UiEffect(){
    }
}