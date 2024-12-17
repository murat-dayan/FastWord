package com.muratdayan.game.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.common.Result
import com.muratdayan.domain.usecase.GetFriendsDomainUseCase
import com.muratdayan.domain.usecase.GetUserStatsDomainUseCase
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
class MainScreenViewModel @Inject constructor(
    private val getFriendsDomainUseCase: GetFriendsDomainUseCase,
    private val getUserStatsDomainUseCase: GetUserStatsDomainUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenContract.UiState())
    val uiState: StateFlow<MainScreenContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<MainScreenContract.UiEffect>() }
    val uiEffect: Flow<MainScreenContract.UiEffect> by  lazy { _uiEffect.receiveAsFlow() }



    fun onAction(action: MainScreenContract.UiAction){
        when(action){
            MainScreenContract.UiAction.PlayNow -> {

            }

            MainScreenContract.UiAction.GetUserStats -> {
                getUserStats()
            }

            MainScreenContract.UiAction.GoToFriends -> {
                viewModelScope.launch {
                    emitUiEffect(MainScreenContract.UiEffect.NavigateToFriendsScreen)
                }
            }
            MainScreenContract.UiAction.GoToLeaderBoard -> {
                viewModelScope.launch {
                    emitUiEffect(MainScreenContract.UiEffect.NavigateToLeaderBoardScreen)
                }
            }
            MainScreenContract.UiAction.GoToSettings -> {
                viewModelScope.launch {
                    emitUiEffect(MainScreenContract.UiEffect.NavigateToSettingsScreen)
                }
            }
            MainScreenContract.UiAction.GoToShop -> {
                viewModelScope.launch {
                    emitUiEffect(MainScreenContract.UiEffect.NavigateToShopScreen)
                }
            }

            is MainScreenContract.UiAction.GoToProfile -> {
                goToProfile(action.userId)
            }

            MainScreenContract.UiAction.GetFriends -> {
                getFriends()
            }
        }
    }

    private fun goToProfile(userId:String) {
        viewModelScope.launch {
            emitUiEffect(MainScreenContract.UiEffect.NavigateToProfileScreen(userId))
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


    private fun updateUiState(block: MainScreenContract.UiState.() -> MainScreenContract.UiState){
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: MainScreenContract.UiEffect){
        _uiEffect.send(uiEffect)
    }

}