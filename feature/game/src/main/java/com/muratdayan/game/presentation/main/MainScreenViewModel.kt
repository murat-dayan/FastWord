package com.muratdayan.game.presentation.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(

): ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenContract.UiState())
    val uiState: StateFlow<MainScreenContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<MainScreenContract.UiEffect>() }
    val uiEffect: Flow<MainScreenContract.UiEffect> by  lazy { _uiEffect.receiveAsFlow() }

    fun onAction(action: MainScreenContract.UiAction){
        when(action){
            MainScreenContract.UiAction.PlayNow -> {

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