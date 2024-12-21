package com.muratdayan.settings.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.muratdayan.navigation.Screen
import com.muratdayan.settings.presentation.SettingsScreenRoot
import com.muratdayan.settings.presentation.SettingsViewModel

fun NavGraphBuilder.settingsNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    composable(
        route = Screen.SettingsScreenRoute.route
    ) {
        val settingsViewModel = hiltViewModel<SettingsViewModel>()
        SettingsScreenRoot(
            modifier = modifier,
            viewModel = settingsViewModel,
            navigateToSignInScreen = {
                navHostController.navigate(Screen.SignInScreenRoute.route) {
                    popUpTo(navHostController.graph.startDestinationId) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            },
            navigateToBack = {
                navHostController.popBackStack()
            }
        )

    }
}