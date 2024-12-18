package com.muratdayan.profile.presentation.profile

import com.muratdayan.domain.model.UserDataModel
import com.muratdayan.domain.model.UserStatsModel
import com.muratdayan.profile.presentation.profile.util.UserType
import io.github.jan.supabase.gotrue.user.UserInfo

object ProfileContract {

    data class UiState(
        val userStats: UserStatsModel? = null,
        val avatarManList: List<String>? = null,
        val avatarGirlList: List<String>? = null,
        val userInfo : UserDataModel? = null,
        val userType: UserType? = null,
        val isLoading: Boolean = false,
    )

    sealed interface UiAction{
        data object GetUserStats: UiAction
        data class CheckUserType(val userId:String): UiAction
        data class SendFriendRequest(val friendId:String): UiAction
        data class GetAvatars(val folderName:String): UiAction
        data class GetUserInfo(val userId:String): UiAction
    }

    sealed class UiEffect(){
    }
}