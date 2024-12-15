package com.muratdayan.settings.presentation

object SettingsContract {

    data class UiState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
    )

    sealed interface UiAction{
        data object Exit:UiAction
    }

    sealed class UiEffect(){
        data object NavigateToSignInScreen: UiEffect()
    }
}