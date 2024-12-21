package com.muratdayan.profile.presentation.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.common.Result
import com.muratdayan.domain.usecase.GetUserInfoDomainUseCase
import com.muratdayan.domain.usecase.GetUserStatsDomainUseCase
import com.muratdayan.profile.domain.usecase.CheckUserTypeUseCase
import com.muratdayan.profile.domain.usecase.GetAvatarsUseCase
import com.muratdayan.profile.domain.usecase.SendFriendRequestUseCase
import com.muratdayan.profile.domain.usecase.UpdateProfileImageUseCase
import com.muratdayan.profile.presentation.profile.util.UserType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserStatsDomainUseCase: GetUserStatsDomainUseCase,
    private val checkUserTypeUseCase: CheckUserTypeUseCase,
    private val sendFriendRequestUseCase: SendFriendRequestUseCase,
    private val getAvatarsUseCase: GetAvatarsUseCase,
    private val getUserInfoDomainUseCase: GetUserInfoDomainUseCase,
    private val updateProfileImageUseCase: UpdateProfileImageUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileContract.UiState())
    val uiState: StateFlow<ProfileContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<ProfileContract.UiEffect>() }
    val uiEffect: Flow<ProfileContract.UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(action: ProfileContract.UiAction) {
        when (action) {
            ProfileContract.UiAction.GetUserStats -> {
                getUserStats()
            }

            is ProfileContract.UiAction.CheckUserType -> {
                checkUserType(action.userId)
            }

            is ProfileContract.UiAction.SendFriendRequest -> {
                sendFriendRequest(action.friendId)
            }

            is ProfileContract.UiAction.GetAvatars -> {
                getAvatars(action.folderName)
            }

            is ProfileContract.UiAction.GetUserInfo -> {
                getUserInfo(action.userId)
            }

            is ProfileContract.UiAction.UpdateProfileImage -> {
                updateProfileImage(action.imageUri)
            }
        }
    }

    private fun updateProfileImage(imageUri: String) {
        updateUiState { copy(isLoading = true) }
        viewModelScope.launch {
            updateProfileImageUseCase.invoke(imageUri)
                .collect{result ->
                    when(result){
                        is Result.Error -> {
                            updateUiState { copy(isLoading = false) }
                            Log.d("ProfileViewModel", "updateProfileImage: ${result.error}")
                        }
                        is Result.Success -> {
                            updateUiState { copy(isLoading = false) }
                            Log.d("ProfileViewModel", "updateProfileImage: ${result.data}")
                            uiState.value.userInfo?.let { getUserInfo(it.id) }
                        }
                    }
                }
        }
    }

    private fun getUserInfo( userId: String) {
        updateUiState { copy(isLoading = true) }

        viewModelScope.launch {
            getUserInfoDomainUseCase.invoke(userId)
                .collect{getUserInfoResult ->
                    when(getUserInfoResult){
                        is Result.Error -> {
                            updateUiState { copy(isLoading = false) }
                        }
                        is Result.Success -> {
                            updateUiState { copy(isLoading = false, userInfo = getUserInfoResult.data) }
                        }
                    }
                }
        }
    }

    private fun getAvatars(folderName: String) {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            getAvatarsUseCase.invoke(folderName)
                .collect{ getAvatarsResult ->
                    when(getAvatarsResult){
                        is Result.Error -> {
                            updateUiState { copy(isLoading = false) }
                        }
                        is Result.Success -> {
                            when(folderName){
                                "mans" ->{
                                    updateUiState { copy(isLoading = false, avatarManList = getAvatarsResult.data) }
                                }
                                "girls" ->{
                                    updateUiState { copy(isLoading = false, avatarGirlList = getAvatarsResult.data) }
                                }
                            }
                        }
                    }
                }
        }
    }

    private fun sendFriendRequest(friendId: String) {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            sendFriendRequestUseCase.invoke(friendId)
                .collect { sendFriendRequestResult ->
                    when (sendFriendRequestResult) {
                        is Result.Error -> {
                            updateUiState { copy(isLoading = false) }
                        }

                        is Result.Success -> {
                            updateUiState { copy(isLoading = false) }
                            checkUserType(friendId)
                        }
                    }
                }
        }
    }

    private fun getUserStats() {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            getUserStatsDomainUseCase.invoke()
                .collect { userStatsResult ->
                    when (userStatsResult) {
                        is Result.Error -> {
                            updateUiState { copy(isLoading = false) }
                        }

                        is Result.Success -> {
                            updateUiState {
                                copy(
                                    isLoading = false,
                                    userStats = userStatsResult.data
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun checkUserType(userId: String) {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            checkUserTypeUseCase.invoke(userId)
                .collect { checkUserTypeResult ->
                    when (checkUserTypeResult) {
                        is Result.Error -> {
                            updateUiState { copy(isLoading = false) }
                        }

                        is Result.Success -> {
                            updateUiState {
                                copy(
                                    isLoading = false,
                                    userType = checkUserTypeResult.data,
                                )
                            }
                            if (checkUserTypeResult.data == UserType.CURRENT) {
                                getUserStats()
                            }
                        }
                    }
                }
        }

    }

    private fun updateUiState(block: ProfileContract.UiState.() -> ProfileContract.UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: ProfileContract.UiEffect) {
        _uiEffect.send(uiEffect)
    }


}