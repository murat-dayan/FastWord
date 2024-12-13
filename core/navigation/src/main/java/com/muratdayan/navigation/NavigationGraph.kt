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
    authNavGraph: (NavGraphBuilder.(modifier: Modifier)->Unit)?,
    gameNavGraph: (NavGraphBuilder.(modifier: Modifier)->Unit)?,
    shopNavGraph: (NavGraphBuilder.(modifier: Modifier)->Unit)?,
    leaderBoardNavGraph: (NavGraphBuilder.(modifier: Modifier)->Unit)?,
    friendsNavGraph: (NavGraphBuilder.(modifier: Modifier)->Unit)?,
    settingsNavGraph: (NavGraphBuilder.(modifier: Modifier)->Unit)?
){

    NavHost(
        navController = navHostController,
        startDestination = if (isSignedIn) Screen.MainScreenRoute.route else Screen.SignInScreenRoute.route
    ) {
        authNavGraph?.invoke(this, modifier)
        gameNavGraph?.invoke(this, modifier)
        shopNavGraph?.invoke(this, modifier)
        leaderBoardNavGraph?.invoke(this, modifier)
        friendsNavGraph?.invoke(this, modifier)
        settingsNavGraph?.invoke(this, modifier)
    }

}