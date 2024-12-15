package com.muratdayan.game.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.muratdayan.game.presentation.main.MainScreenRoot
import com.muratdayan.game.presentation.main.MainScreenViewModel
import com.muratdayan.navigation.Screen

fun NavGraphBuilder.gameNavGraph(
    modifier: Modifier = Modifier,
    navigateToShop: () -> Unit,
    navigateToSettings: () -> Unit,
    navigateToFriends: () -> Unit,
    navigateToLeaderBoard: () -> Unit,
    navigateToProfile: () -> Unit,
) {
    composable(
        route = Screen.MainScreenRoute.route
    ) {
        val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()
        MainScreenRoot(
            modifier = modifier,
            mainScreenViewModel = mainScreenViewModel,
            navigateToShop = navigateToShop,
            navigateToSettings = navigateToSettings,
            navigateToFriends = navigateToFriends,
            navigateToLeaderBoard = navigateToLeaderBoard,
            navigateToProfile = navigateToProfile

        )

    }
}