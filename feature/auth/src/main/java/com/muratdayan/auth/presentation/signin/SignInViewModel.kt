package com.muratdayan.auth.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class SignInViewModel @Inject constructor(): ViewModel() {


    private val _uiState = MutableStateFlow(SignInContract.UiState())
    val uiState: StateFlow<SignInContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<SignInContract.UiEffect>() }
    val uiEffect: Flow<SignInContract.UiEffect> by  lazy { _uiEffect.receiveAsFlow() }

    fun onAction(action: SignInContract.UiAction){
        when(action){
            SignInContract.UiAction.FacebookSignIn -> {}
            SignInContract.UiAction.GuestSignIn -> {
                navigateToGame()
            }
        }
    }

    private fun navigateToGame(){
        viewModelScope.launch {
            emitUiEffect(SignInContract.UiEffect.NavigateToMainScreen)
        }
    }

    private fun updateUiState(block: SignInContract.UiState.() -> SignInContract.UiState){
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: SignInContract.UiEffect){
        _uiEffect.send(uiEffect)
    }

}