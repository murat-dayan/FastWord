package com.muratdayan.game.presentation.start

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.muratdayan.common.Result
import com.muratdayan.domain.usecase.GetUserInfoDomainUseCase
import com.muratdayan.game.domain.usecase.GetRandomQuestionUseCase
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
    private val getRoomUseCase: GetRoomUseCase,
    private val getRandomQuestionUseCase: GetRandomQuestionUseCase
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

            StartContract.UiAction.GetRandomQuestion -> {
                getRandomQuestion()
            }
        }
    }

    private fun getRandomQuestion(){
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }

            getRandomQuestionUseCase.invoke()
                .collect{getRandomQuestionResult ->
                    when(getRandomQuestionResult){
                        is Result.Error -> {
                            updateUiState { copy(isLoading = false) }
                            Log.e("StartViewModel","getRandomQuestion: ${getRandomQuestionResult.error}")
                        }
                        is Result.Success -> {
                            updateUiState { copy(isLoading = false,question = getRandomQuestionResult.data) }
                        }
                    }
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