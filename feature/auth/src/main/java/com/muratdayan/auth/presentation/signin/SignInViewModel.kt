package com.muratdayan.auth.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.auth.domain.usecase.FacebookSignInUseCase
import com.muratdayan.auth.domain.usecase.GetFacebookSignUrlUseCase
import com.muratdayan.auth.domain.usecase.GuestSignInUseCase
import com.muratdayan.common.LoginStateManager
import com.muratdayan.common.Result
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
class SignInViewModel @Inject constructor(
    private val guestSignInUseCase: GuestSignInUseCase,
    private val facebookSignInUseCase: FacebookSignInUseCase,
    private val getFacebookSignUrlUseCase: GetFacebookSignUrlUseCase,
    private val loginStateManager: LoginStateManager
) : ViewModel() {


    private val _uiState = MutableStateFlow(SignInContract.UiState())
    val uiState: StateFlow<SignInContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<SignInContract.UiEffect>() }
    val uiEffect: Flow<SignInContract.UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(action: SignInContract.UiAction) {
        when (action) {
            SignInContract.UiAction.FacebookSignIn -> {
                viewModelScope.launch {
                    handleFacebookSignIn()
                }
            }

            SignInContract.UiAction.GuestSignIn -> {
                guestSignIn()
            }

            SignInContract.UiAction.ClearFacebookUrl -> {
                clearFacebookUri()
            }
        }
    }

    private suspend fun handleFacebookSignIn() {
        try {

            val url = getFacebookSignUrlUseCase.invoke()
            emitUiEffect(SignInContract.UiEffect.OpenCustomTab(url))
            updateUiState { copy(facebookSignInUrl = url) }

            facebookSignInUseCase.invoke().collect { result ->
                updateUiState { copy(isLoading = true) }
                when (result) {
                    is Result.Error -> {
                        updateUiState {
                            copy(
                                errorMessage = result.error.message,
                                isLoading = false
                            )
                        }
                    }

                    is Result.Success -> {
                        updateUiState { copy(isFacebookSignInEnabled = false, isLoading = false) }
                        emitUiEffect(SignInContract.UiEffect.NavigateToMainScreen)
                    }
                }
            }
        } catch (e: Exception) {
            updateUiState { copy(errorMessage = e.message, isLoading = false) }

        }
    }

    private fun clearFacebookUri(){
        updateUiState { copy(facebookSignInUrl = null) }
    }

    private fun guestSignIn() {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            guestSignInUseCase.invoke().collect { result ->
                when (result) {
                    is Result.Error -> {

                        updateUiState {
                            copy(
                                errorMessage = result.error.message,
                                isLoading = false
                            )
                        }
                    }

                    is Result.Success -> {
                        loginStateManager.setLoggedIn(true)
                        updateUiState { copy(isGuestSignInEnabled = false, isLoading = false) }
                        emitUiEffect(SignInContract.UiEffect.NavigateToMainScreen)
                    }
                }
            }
        }
    }



    private fun updateUiState(block: SignInContract.UiState.() -> SignInContract.UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: SignInContract.UiEffect) {
        _uiEffect.send(uiEffect)
    }

}