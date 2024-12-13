package com.muratdayan.game.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.common.Result
import com.muratdayan.game.domain.usecase.GetUserStatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getUserStatsUseCase: GetUserStatsUseCase
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
        }
    }

    private fun getUserStats() {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            getUserStatsUseCase.invoke()
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