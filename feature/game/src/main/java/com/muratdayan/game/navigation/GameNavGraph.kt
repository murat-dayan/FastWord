package com.muratdayan.game.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.muratdayan.game.presentation.main.MainScreenRoot
import com.muratdayan.navigation.Screen

fun NavGraphBuilder.gameNavGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = Screen.MainScreenRoute.route
    ) {
        MainScreenRoot(
            modifier = modifier,
        )

    }
}