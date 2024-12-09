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
    gameNavGraph: (NavGraphBuilder.(modifier: Modifier)->Unit)?
){

    NavHost(
        navController = navHostController,
        startDestination = if (isSignedIn) Screen.MainScreenRoute.route else Screen.SignInScreenRoute.route
    ) {
        authNavGraph?.invoke(this, modifier)
        gameNavGraph?.invoke(this, modifier)
    }

}