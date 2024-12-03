package com.muratdayan.auth.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.muratdayan.auth.presentation.signin.SignInScreenRoot
import com.muratdayan.navigation.Screen


fun NavGraphBuilder.authNavGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = Screen.SignInScreenRoute.route
    ) {
        SignInScreenRoot(
            modifier = modifier,
        )

    }
}