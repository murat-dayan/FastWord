package com.muratdayan.leaderboard.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.muratdayan.leaderboard.presentation.LeaderBoardScreenRoot
import com.muratdayan.leaderboard.presentation.LeaderBoardViewModel
import com.muratdayan.navigation.Screen

fun NavGraphBuilder.leaderBoardNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    composable(
        route = Screen.LeaderBoardScreenRoute.route
    ) {
        val leaderBoardViewModel = hiltViewModel<LeaderBoardViewModel>()
        LeaderBoardScreenRoot(
            modifier = modifier,
            leaderBoardViewModel = leaderBoardViewModel,
            navigateToProfile = {userId->
                navHostController.navigate(Screen.ProfileScreenRoute.withUserId(userId))
            },
            navigateToBack = {
                navHostController.popBackStack()
            }
        )

    }
}