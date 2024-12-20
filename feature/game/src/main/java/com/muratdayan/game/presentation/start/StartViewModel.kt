package com.muratdayan.game.presentation.start

import androidx.lifecycle.viewModelScope
import com.muratdayan.common.Result
import com.muratdayan.domain.usecase.GetUserInfoDomainUseCase
import com.muratdayan.game.domain.usecase.GetRoomUseCase
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    getUserInfoDomainUseCase: GetUserInfoDomainUseCase,
    private val getRoomUseCase: GetRoomUseCase
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

            is StartContract.UiAction.GetOpponentInfo -> {
                getOpponentInfo(action.opponentId)
            }

            is StartContract.UiAction.GetRoom -> {
                getRoom(action.roomId)
            }
        }
    }

    private fun getOpponentInfo(opponentId:String){
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            val opponentInfo = fetchOpponentInfo(opponentId)

            if (opponentInfo != null) {
                updateUiState { copy(isLoading = false,opponentInfo = opponentInfo) }
            }
        }
    }

    private fun getRoom(roomId:String){
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }

            getRoomUseCase(roomId).collect{getRoomResult ->
                when(getRoomResult){
                    is Result.Error -> {
                        updateUiState { copy(isLoading = false) }
                    }
                    is Result.Success -> {
                        updateUiState { copy(isLoading = false,room = getRoomResult.data) }
                    }
                }
            }
        }
    }



    private fun getUserInfo(){
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            val userInfo = fetchUserInfo()

            if (userInfo != null) {
                updateUiState { copy(isLoading = false,userInfo = userInfo) }
            }
        }
    }



    private fun updateUiState(block: StartContract.UiState.() -> StartContract.UiState){
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: StartContract.UiEffect){
        _uiEffect.send(uiEffect)
    }




}