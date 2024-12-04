package com.muratdayan.auth.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.muratdayan.auth.presentation.signin.SignInScreenRoot
import com.muratdayan.auth.presentation.signin.SignInViewModel
import com.muratdayan.navigation.Screen


fun NavGraphBuilder.authNavGraph(
    modifier: Modifier = Modifier,
    navigateToGame: ()->Unit
) {
    composable(
        route = Screen.SignInScreenRoute.route
    ) {
        val signInViewModel = hiltViewModel<SignInViewModel>()
        SignInScreenRoot(
            modifier = modifier,
            signInViewModel = signInViewModel,
            navigateToGame = navigateToGame
        )

    }
}