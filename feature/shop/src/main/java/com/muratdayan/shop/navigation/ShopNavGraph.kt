package com.muratdayan.shop.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.muratdayan.navigation.Screen
import com.muratdayan.shop.presentation.ShopScreenRoot

fun NavGraphBuilder.shopNavGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = Screen.ShopScreenRoute.route
    ) {
        val shopViewModel = hiltViewModel<com.muratdayan.shop.presentation.ShopViewModel>()
        ShopScreenRoot(
            modifier = modifier,
            shopViewModel = shopViewModel
        )

    }
}