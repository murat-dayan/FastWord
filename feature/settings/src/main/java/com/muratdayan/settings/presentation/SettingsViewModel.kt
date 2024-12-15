package com.muratdayan.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.common.Result
import com.muratdayan.settings.domain.usecase.ExitAppUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val exitAppUseCase: ExitAppUseCase
) : ViewModel(){

    private val _uiState = MutableStateFlow(SettingsContract.UiState())
    val uiState: StateFlow<SettingsContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<SettingsContract.UiEffect>() }
    val uiEffect: Flow<SettingsContract.UiEffect> by  lazy { _uiEffect.receiveAsFlow() }

    fun onAction(action: SettingsContract.UiAction){
        when(action){
            SettingsContract.UiAction.Exit -> {
                exitApp()
            }
        }
    }

    private fun exitApp() {
        updateUiState { copy(isLoading = true) }
        viewModelScope.launch {
            exitAppUseCase.invoke().collect{result->
                when(result){
                    is Result.Error -> {
                        updateUiState { copy(isLoading = false, errorMessage = result.error.message) }
                    }
                    is Result.Success -> {
                        updateUiState { copy(isLoading = false) }
                        emitUiEffect(SettingsContract.UiEffect.NavigateToSignInScreen)
                    }
                }
            }
        }
    }

    private fun updateUiState(block: SettingsContract.UiState.() -> SettingsContract.UiState){
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: SettingsContract.UiEffect){
        _uiEffect.send(uiEffect)
    }

}