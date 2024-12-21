package com.muratdayan.game.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.muratdayan.game.presentation.main.MainScreenRoot
import com.muratdayan.game.presentation.main.MainScreenViewModel
import com.muratdayan.game.presentation.match.MatchScreenRoot
import com.muratdayan.game.presentation.match.MatchViewModel
import com.muratdayan.game.presentation.start.StartScreenRoot
import com.muratdayan.navigation.Screen

fun NavGraphBuilder.gameNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    composable(
        route = Screen.MainScreenRoute.route
    ) {
        val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()
        MainScreenRoot(
            modifier = modifier,
            mainScreenViewModel = mainScreenViewModel,
            navigateToShop = {
                navHostController.navigate(Screen.ShopScreenRoute.route)
            },
            navigateToSettings = {
                navHostController.navigate(Screen.SettingsScreenRoute.route)
            },
            navigateToFriends = {
                navHostController.navigate(Screen.FriendsScreenRoute.route)
            },
            navigateToLeaderBoard = {
                navHostController.navigate(Screen.LeaderBoardScreenRoute.route)
            },
            navigateToProfile = {
                navHostController.navigate(Screen.ProfileScreenRoute.withUserId(it))
            },
            navigateToMatch = {
                navHostController.navigate(Screen.MatchScreenRoute.route)
            }
        )

    }

    composable(
        route = Screen.MatchScreenRoute.route
    ){
        val matchViewModel = hiltViewModel<MatchViewModel>()
        MatchScreenRoot(
            modifier = modifier,
            matchViewModel = matchViewModel,
            navigateToStartScreen = {
                navHostController.navigate(Screen.StartScreenRoute.withOpponentUserId())
            },
            navigateToBack = {
                navHostController.popBackStack()
            }
        )
    }

    composable(
        route = Screen.StartScreenRoute.route
    ){

        StartScreenRoot(
            modifier = modifier,
        )
    }


}