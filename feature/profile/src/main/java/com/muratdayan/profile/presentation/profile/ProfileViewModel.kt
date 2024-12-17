package com.muratdayan.profile.presentation.profile

import androidx.lifecycle.ViewModel
import com.muratdayan.domain.usecase.GetUserStatsDomainUseCase
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
class ProfileViewModel @Inject constructor(
    private val getUserStatsDomainUseCase: GetUserStatsDomainUseCase
) : ViewModel(){

    private val _uiState = MutableStateFlow(ProfileContract.UiState())
    val uiState: StateFlow<ProfileContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<ProfileContract.UiEffect>() }
    val uiEffect: Flow<ProfileContract.UiEffect> by  lazy { _uiEffect.receiveAsFlow() }

    fun onAction(action: ProfileContract.UiAction){
        when(action){
            ProfileContract.UiAction.GetUserStats -> {

            }
        }
    }

    private fun updateUiState(block: ProfileContract.UiState.() -> ProfileContract.UiState){
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: ProfileContract.UiEffect){
        _uiEffect.send(uiEffect)
    }


}