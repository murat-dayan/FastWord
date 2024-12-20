package com.muratdayan.game.presentation.start

import com.muratdayan.domain.usecase.GetUserInfoDomainUseCase
import com.muratdayan.game.presentation.base.BaseViewModel
import com.muratdayan.game.presentation.match.MatchContract
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
class StartViewModel @Inject constructor(
    private val getUserInfoDomainUseCase: GetUserInfoDomainUseCase
) : BaseViewModel(getUserInfoDomainUseCase){

    private val _uiState = MutableStateFlow(StartContract.UiState())
    val uiState: StateFlow<StartContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<StartContract.UiEffect>() }
    val uiEffect: Flow<StartContract.UiEffect> by  lazy { _uiEffect.receiveAsFlow() }

    fun onAction(action: StartContract.UiAction){
        when(action){
            StartContract.UiAction.GetUserInfo -> {
                getUserInfo()
            }
        }
    }

    private fun getUserInfo(){

    }



    private fun updateUiState(block: StartContract.UiState.() -> StartContract.UiState){
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: StartContract.UiEffect){
        _uiEffect.send(uiEffect)
    }




}