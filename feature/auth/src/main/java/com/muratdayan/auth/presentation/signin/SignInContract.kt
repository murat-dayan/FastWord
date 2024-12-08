package com.muratdayan.auth.presentation.signin

import android.content.Context

object SignInContract {

    data class UiState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val isGuestSignInEnabled: Boolean = true,
        val isFacebookSignInEnabled: Boolean = true,
        val facebookSignInUrl: String? = null
    )

    sealed interface UiAction{
        data object FacebookSignIn: UiAction
        data object GuestSignIn: UiAction
        data object ClearFacebookUrl: UiAction
    }

    sealed class UiEffect(){
        data object NavigateToMainScreen: UiEffect()
        data class OpenCustomTab(val uri: String): UiEffect()
    }
}