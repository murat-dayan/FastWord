package com.muratdayan.friends.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.muratdayan.friends.presentation.FriendsScreenRoot
import com.muratdayan.navigation.Screen

fun NavGraphBuilder.friendsNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    composable(
        route = Screen.FriendsScreenRoute.route
    ) {
        val friendsViewModel = hiltViewModel<com.muratdayan.friends.presentation.FriendsViewModel>()
        FriendsScreenRoot(
            modifier = modifier,
            friendsViewModel = friendsViewModel,
            navigateToBack = {
                navHostController.popBackStack()
            }
        )

    }
}