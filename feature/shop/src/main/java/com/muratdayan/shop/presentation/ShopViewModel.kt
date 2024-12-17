package com.muratdayan.shop.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.common.Result
import com.muratdayan.common.StatType
import com.muratdayan.domain.usecase.GetUserStatsDomainUseCase
import com.muratdayan.domain.usecase.UpdateStatsUseCase
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
import java.lang.Math.abs
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val getUserStatsDomainUseCase: GetUserStatsDomainUseCase,
    private val updateStatsUseCase: UpdateStatsUseCase
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

            is ShopScreenContract.UiAction.BuyEnergy -> {
                buyEnergy(
                    changeEnergyValue = action.changeEnergyValue,
                    changeEmeraldValue = action.changeEmeraldValue
                )
            }

            is ShopScreenContract.UiAction.BuyToken -> {
                buyToken(
                    changeTokenValue = action.changeTokenValue,
                    changeEmeraldValue = action.changeEmeraldValue
                )
            }
        }
    }

    private fun buyToken(changeTokenValue:Int,changeEmeraldValue: Int){
            val currentToken = uiState.value.userStats?.token
            val currentEmerald = uiState.value.userStats?.emerald
            if (currentToken != null && currentEmerald != null) {
                if (kotlin.math.abs(currentEmerald) >= kotlin.math.abs(changeEmeraldValue) ){
                    val newTokenValue = currentToken + changeTokenValue
                    viewModelScope.launch {
                        updateStatsUseCase.updateStat(StatType.TOKEN,newTokenValue)
                            .collect{resultUpdateToken->
                                when(resultUpdateToken){
                                    is Result.Success -> {
                                        getUserStats()
                                        updateEmerald(changeEmeraldValue)
                                    }
                                    is Result.Error -> {

                                    }
                                }
                            }
                    }
                }
            }

    }

    private fun buyEnergy(changeEnergyValue:Int, changeEmeraldValue:Int) {
        viewModelScope.launch {
            val currentEnergy = uiState.value.userStats?.energy
            currentEnergy?.let {
                val newEnergyValue = currentEnergy + changeEnergyValue
                updateStatsUseCase.updateStat(StatType.ENERGY,newEnergyValue)
                    .collect{resultUpdateEnergy->
                        when(resultUpdateEnergy){
                            is Result.Success -> {
                                getUserStats()
                                updateEmerald(changeEmeraldValue)
                            }
                            is Result.Error -> {

                            }
                        }
                    }
            }
        }
    }

    private fun updateEmerald(changeEmeraldValue:Int) {
        viewModelScope.launch {
            val currentEmeraldValue = uiState.value.userStats?.emerald
            currentEmeraldValue?.let {
                val newEmeraldValue = currentEmeraldValue + changeEmeraldValue
                updateStatsUseCase.updateStat(StatType.EMERALD,newEmeraldValue)
                    .collect { resultUpdateEmerald ->
                        when (resultUpdateEmerald) {
                            is Result.Success -> {
                                getUserStats()
                            }

                            is Result.Error -> {

                            }
                        }
                    }
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