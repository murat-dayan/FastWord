package com.muratdayan.game.presentation.main

object MainScreenContract {

    data class UiState(
        val isLoading: Boolean = false,
    )

    sealed interface UiAction{
        data object PlayNow:UiAction
    }

    sealed class UiEffect(){
        data object NavigateToPlayScreen: UiEffect()
    }
}