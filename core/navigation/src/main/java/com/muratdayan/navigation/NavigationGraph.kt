package com.muratdayan.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    isSignedIn: Boolean,
    navHostController: NavHostController,
    navGraphDependencies: NavGraphDependencies
){

    NavHost(
        navController = navHostController,
        startDestination = if (isSignedIn) Screen.MainScreenRoute.route else Screen.SignInScreenRoute.route
    ) {
        navGraphDependencies.authNavGraph?.invoke(this, modifier)
        navGraphDependencies.gameNavGraph?.invoke(this, modifier)
        navGraphDependencies.shopNavGraph?.invoke(this, modifier)
        navGraphDependencies.leaderBoardNavGraph?.invoke(this, modifier)
        navGraphDependencies.friendsNavGraph?.invoke(this, modifier)
        navGraphDependencies.settingsNavGraph?.invoke(this, modifier)
        navGraphDependencies.profileNavGraph?.invoke(this, modifier)

    }

}