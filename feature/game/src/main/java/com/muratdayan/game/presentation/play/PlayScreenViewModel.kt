package com.muratdayan.game.presentation.play

import com.muratdayan.domain.usecase.GetUserInfoDomainUseCase
import com.muratdayan.game.presentation.base.BaseViewModel
import com.muratdayan.game.presentation.start.StartContract
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
class PlayScreenViewModel @Inject constructor(
    getUserInfoDomainUseCase: GetUserInfoDomainUseCase
) : BaseViewModel(getUserInfoDomainUseCase) {

    private val _uiState = MutableStateFlow(PlayScreenContract.UiState())
    val uiState: StateFlow<PlayScreenContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<PlayScreenContract.UiEffect>() }
    val uiEffect: Flow<PlayScreenContract.UiEffect> by lazy { _uiEffect.receiveAsFlow() }


    fun onAction(action: PlayScreenContract.UiAction) {
        when (action) {
            PlayScreenContract.UiAction.GetUserInfo -> {
                getUserInfo()
            }

            is PlayScreenContract.UiAction.GetOpponentInfo -> {
                getOpponentInfo(action.opponentId)
            }
        }
    }

    private fun getUserInfo(){

    }

    private fun getOpponentInfo(opponentId:String){

    }


    private fun updateUiState(block: PlayScreenContract.UiState.() -> PlayScreenContract.UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: PlayScreenContract.UiEffect) {
        _uiEffect.send(uiEffect)
    }
}