package com.muratdayan.friends.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.common.Result
import com.muratdayan.domain.usecase.GetFriendsDomainUseCase
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
    private val getFriendsDomainUseCase: GetFriendsDomainUseCase
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
            FriendsContract.UiAction.GetUserStats -> {}
            FriendsContract.UiAction.GoToProfile -> {}
            FriendsContract.UiAction.GoToShop -> {}
            FriendsContract.UiAction.InviteFriends -> {}
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