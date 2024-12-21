package com.muratdayan.leaderboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.common.Result
import com.muratdayan.domain.usecase.GetFriendsDomainUseCase
import com.muratdayan.leaderboard.domain.usecase.GetEveryoneUseCase
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
class LeaderBoardViewModel @Inject constructor(
    private val getFriendsDomainUseCase: GetFriendsDomainUseCase,
    private val getEveryoneUseCase: GetEveryoneUseCase
)  : ViewModel(){


    private val _uiState = MutableStateFlow(LeaderBoardContract.UiState())
    val uiState: StateFlow<LeaderBoardContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<LeaderBoardContract.UiEffect>() }
    val uiEffect: Flow<LeaderBoardContract.UiEffect> by  lazy { _uiEffect.receiveAsFlow() }

    fun onAction(action: LeaderBoardContract.UiAction){
        when(action) {
            LeaderBoardContract.UiAction.GetFriends -> {
                getFriends()
            }

            LeaderBoardContract.UiAction.GetEveryone -> {
                getEveryone()
            }

            is LeaderBoardContract.UiAction.GoToProfile -> {
                goToProfile(action.userId)
            }

            LeaderBoardContract.UiAction.NavigateToBack -> {
                viewModelScope.launch {
                    emitUiEffect(LeaderBoardContract.UiEffect.NavigateToBack)
                }
            }
        }
    }

    private fun goToProfile(userId: String) {
        viewModelScope.launch {
            emitUiEffect(LeaderBoardContract.UiEffect.NavigateToProfileScreen(userId))
        }
    }

    private fun getEveryone() {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }

            getEveryoneUseCase.invoke()
                .onStart {
                    updateUiState { copy(isLoading = true) }
                }
                .catch {
                    updateUiState { copy(isLoading = false, everyoneList = null) }
                }
                .collect{resultEveryoneState->
                    when(resultEveryoneState){
                        is Result.Error -> {
                            updateUiState { copy(isLoading = false, everyoneList = null) }
                        }
                        is Result.Success -> {
                            updateUiState {
                                copy(isLoading = false, everyoneList = resultEveryoneState.data)
                            }
                        }
                    }
                }
        }
    }

    private fun getFriends() {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }

            getFriendsDomainUseCase.invoke()
                .onStart {
                    updateUiState { copy(isLoading = true) }
                }
                .catch {
                    updateUiState { copy(isLoading = false, friends = null) }
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


    private fun updateUiState(block: LeaderBoardContract.UiState.() -> LeaderBoardContract.UiState){
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: LeaderBoardContract.UiEffect){
        _uiEffect.send(uiEffect)
    }


}