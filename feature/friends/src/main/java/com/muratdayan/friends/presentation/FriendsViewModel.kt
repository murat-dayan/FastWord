package com.muratdayan.friends.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.common.Result
import com.muratdayan.domain.usecase.GetFriendsDomainUseCase
import com.muratdayan.domain.usecase.GetUserStatsDomainUseCase
import com.muratdayan.domain.usecase.UpdateFriendStatusDomainUseCase
import com.muratdayan.friends.domain.usecase.GetPendingFriendsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val getFriendsDomainUseCase: GetFriendsDomainUseCase,
    private val getUserStatsDomainUseCase: GetUserStatsDomainUseCase,
    private val getPendingFriendsUseCase: GetPendingFriendsUseCase,
    private val updateFriendStatusDomainUseCase: UpdateFriendStatusDomainUseCase
) : ViewModel(){


    private val _uiState = MutableStateFlow(FriendsContract.UiState())
    val uiState: StateFlow<FriendsContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<FriendsContract.UiEffect>() }
    val uiEffect: Flow<FriendsContract.UiEffect> by  lazy { _uiEffect.receiveAsFlow() }

    fun onAction(action: FriendsContract.UiAction){
        when(action){
            FriendsContract.UiAction.GetFriends -> {
                getFriends()
            }
            FriendsContract.UiAction.GetUserStats -> {
                getUserStats()
            }
            FriendsContract.UiAction.GoToProfile -> {}
            FriendsContract.UiAction.GoToShop -> {}
            FriendsContract.UiAction.InviteFriends -> {}
            FriendsContract.UiAction.GetPendingFriends -> {
                getPendingFriends()
            }

            is FriendsContract.UiAction.UpdateFriendStatus -> {
                updateFriendStatus(action.senderId,action.status)
            }
        }
    }

    private fun updateFriendStatus(senderId: String, status: String) {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            updateFriendStatusDomainUseCase.invoke(senderId = senderId, statusValue = status)
                .collect{resultUpdateFriendStatus->
                    when(resultUpdateFriendStatus){
                        is Result.Error -> {
                            updateUiState { copy(isLoading = false) }
                        }
                        is Result.Success -> {
                            updateUiState { copy(isLoading = false) }
                            getPendingFriends()
                            getFriends()
                        }
                    }
                }
        }
    }

    private fun getPendingFriends() {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            getPendingFriendsUseCase.invoke()
                .collect{resultPendingFriendsState->
                    when(resultPendingFriendsState){
                        is Result.Error -> {
                            updateUiState { copy(isLoading = false, pendingFriends = emptyList()) }
                        }
                        is Result.Success -> {
                            updateUiState {
                                copy(isLoading = false, pendingFriends = resultPendingFriendsState.data)
                            }
                        }
                    }
                }
        }
    }

    private fun getUserStats() {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            getUserStatsDomainUseCase.invoke()
                .onStart {
                    updateUiState { copy(isLoading = true) }
                }
                .catch {
                    updateUiState { copy(isLoading = false, userStats = null) }
                }
                .collect { resultUserState ->
                    when (resultUserState) {
                        is Result.Success -> {
                            updateUiState {
                                copy(isLoading = false, userStats = resultUserState.data)
                            }
                        }
                        is Result.Error -> {
                            updateUiState { copy(isLoading = false, userStats = null) }
                        }
                    }
                }
        }

    }

    private fun getFriends(){
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }

            getFriendsDomainUseCase.invoke()
                .onStart {
                    updateUiState { copy(isLoading = true) }
                }
                .catch {
                    updateUiState { copy(isLoading = false, userStats = null) }
                }
                .collect{resultFriendsState->
                    when(resultFriendsState){
                        is Result.Error -> {
                            updateUiState { copy(isLoading = false, friends = null) }
                        }
                        is Result.Success -> {
                            updateUiState {
                                copy(isLoading = false, friends = resultFriendsState.data)
                            }
                        }
                    }
                }
        }
    }


    private fun updateUiState(block: FriendsContract.UiState.() -> FriendsContract.UiState){
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: FriendsContract.UiEffect){
        _uiEffect.send(uiEffect)
    }
}