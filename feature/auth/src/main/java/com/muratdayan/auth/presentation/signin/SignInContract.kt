package com.muratdayan.auth.presentation.signin

object SignInContract {

    data class UiState(
        val isLoading: Boolean = false,
    )

    sealed interface UiAction{
        data object FacebookSignIn: UiAction
        data object GuestSignIn: UiAction
    }

    sealed class UiEffect(){
        data object NavigateToMainScreen: UiEffect()
    }
}