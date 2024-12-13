package com.muratdayan.leaderboard.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.muratdayan.leaderboard.presentation.LeaderBoardScreenRoot
import com.muratdayan.navigation.Screen

fun NavGraphBuilder.leaderBoardNavGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = Screen.LeaderBoardScreenRoute.route
    ) {
        LeaderBoardScreenRoot(
            modifier = modifier,
        )

    }
}