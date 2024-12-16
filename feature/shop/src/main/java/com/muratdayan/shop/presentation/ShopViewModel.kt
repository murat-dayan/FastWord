package com.muratdayan.shop.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.common.Result
import com.muratdayan.domain.usecase.GetUserStatsDomainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val getUserStatsDomainUseCase: GetUserStatsDomainUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ShopScreenContract.UiState())
    val uiState: StateFlow<ShopScreenContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<ShopScreenContract.UiEffect>() }
    val uiEffect: Flow<ShopScreenContract.UiEffect> by  lazy { _uiEffect.receiveAsFlow() }

    fun onAction(action: ShopScreenContract.UiAction){
        when(action){
            ShopScreenContract.UiAction.GetUserStats -> {
                getUserStats()
            }
        }
    }

    private fun getUserStats() {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            getUserStatsDomainUseCase.invoke()
                .onStart {
                    updateUiState { copy(isLoading = true) }
                }
                .catch {
                    updateUiState { copy(isLoading = false, userStats = null) }
                }
                .collect { resultUserState ->
                    when (resultUserState) {
                        is Result.Success -> {
                            updateUiState {
                                copy(isLoading = false, userStats = resultUserState.data)
                            }
                        }
                        is Result.Error -> {
                            updateUiState { copy(isLoading = false, userStats = null) }
                        }
                    }
                }
        }

    }



    private fun updateUiState(block: ShopScreenContract.UiState.() -> ShopScreenContract.UiState){
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: ShopScreenContract.UiEffect){
        _uiEffect.send(uiEffect)
    }
}