package com.muratdayan.profile.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.common.Result
import com.muratdayan.domain.usecase.GetUserStatsDomainUseCase
import com.muratdayan.profile.domain.usecase.CheckUserTypeUseCase
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
class ProfileViewModel @Inject constructor(
    private val getUserStatsDomainUseCase: GetUserStatsDomainUseCase,
    private val checkUserTypeUseCase: CheckUserTypeUseCase
) : ViewModel(){

    private val _uiState = MutableStateFlow(ProfileContract.UiState())
    val uiState: StateFlow<ProfileContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<ProfileContract.UiEffect>() }
    val uiEffect: Flow<ProfileContract.UiEffect> by  lazy { _uiEffect.receiveAsFlow() }

    fun onAction(action: ProfileContract.UiAction){
        when(action){
            ProfileContract.UiAction.GetUserStats -> {

            }

            is ProfileContract.UiAction.CheckUserType -> {
                checkUserType(action.userId)
            }
        }
    }

    private fun checkUserType(userId: String) {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            checkUserTypeUseCase.invoke(userId)
                .collect{checkUserTypeResult->
                    when(checkUserTypeResult){
                        is Result.Error -> {
                            updateUiState { copy(isLoading = false) }
                        }
                        is Result.Success -> {
                            updateUiState { copy(isLoading = false, userType = checkUserTypeResult.data) }
                        }
                    }
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