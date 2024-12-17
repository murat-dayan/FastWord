package com.muratdayan.shop.presentation

object ShopScreenContract {

    data class UiState(
        val userStats: com.muratdayan.domain.model.UserStatsModel? = null,
        val isLoading: Boolean = false,
    )

    sealed interface UiAction{
        data object GetUserStats: UiAction
        data class BuyEnergy(val changeEnergyValue:Int,val changeEmeraldValue:Int): UiAction
        data class BuyToken(val changeTokenValue:Int,val changeEmeraldValue:Int): UiAction
    }

    sealed class UiEffect(){
    }
}