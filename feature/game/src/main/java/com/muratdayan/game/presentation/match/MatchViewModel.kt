package com.muratdayan.game.presentation.match

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.muratdayan.common.Result
import com.muratdayan.domain.usecase.GetUserInfoDomainUseCase
import com.muratdayan.game.domain.model.MatchResult
import com.muratdayan.game.domain.usecase.FindOrCreateRoomUseCase
import com.muratdayan.game.domain.usecase.StartRealtimeRoomListenerUseCase
import com.muratdayan.game.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    getUserInfoDomainUseCase: GetUserInfoDomainUseCase,
    private val findOrCreateRoomUseCase: FindOrCreateRoomUseCase,
    private val startRealtimeRoomListenerUseCase: StartRealtimeRoomListenerUseCase
) : BaseViewModel(getUserInfoDomainUseCase){

    private val _uiState = MutableStateFlow(MatchContract.UiState())
    val uiState: StateFlow<MatchContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<MatchContract.UiEffect>() }
    val uiEffect: Flow<MatchContract.UiEffect> by  lazy { _uiEffect.receiveAsFlow() }

    fun onAction(action: MatchContract.UiAction){
        when(action){
            MatchContract.UiAction.GetUserInfo -> {
                getUserInfo()
            }

            is MatchContract.UiAction.FindOrCreateRoom -> {
                findOrCreateRoom(action.userId)
            }

            MatchContract.UiAction.GoToStartScreen -> {
                viewModelScope.launch {
                    emitUiEffect(MatchContract.UiEffect.NavigateToStartScreen)
                }
            }
        }

    }

    private fun getUserInfo(){
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            val userInfo = fetchUserInfo()

            // userInfo null değilse, FindOrCreateRoom aksiyonunu başlatıyoruz
            if (userInfo != null) {
                updateUiState { copy(isLoading = false,userInfo = userInfo) }
            }
        }
    }

    private fun findOrCreateRoom(userId:String){
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            findOrCreateRoomUseCase.invoke(userId).collect { result ->
                when (result) {
                    is Result.Error -> {
                        updateUiState { copy(isLoading = false) }
                    }
                    is Result.Success -> {
                        when(result.data){
                            MatchResult.NoAvailableRoom -> {
                                updateUiState { copy(isLoading = false) }
                                Log.d("MatchViewModel", "findOrCreateRoom: NoAvailableRoom")
                            }
                            is MatchResult.RoomCreated -> {
                                Log.d("MatchViewModel", "findOrCreateRoom: RoomCreated")
                                (result.data as MatchResult.RoomCreated).room.id?.let {
                                    startRealtimeRoomListenerUseCase.invoke(
                                        it
                                    ).onEach { room->
                                        Log.d("MatchViewModel", "findOrCreateRoom: $room")
                                        updateUiState { copy(isLoading = false, room = room) }
                                    }.launchIn(viewModelScope)
                                }
                            }
                            is MatchResult.RoomFound -> {
                                Log.d("MatchViewModel", "findOrCreateRoom: RoomFound")
                                updateUiState { copy(isLoading = false, room = (result.data as MatchResult.RoomFound).room) }
                            }
                        }
                    }
                }
            }
        }
    }


    private fun updateUiState(block: MatchContract.UiState.() -> MatchContract.UiState){
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: MatchContract.UiEffect){
        _uiEffect.send(uiEffect)
    }

}