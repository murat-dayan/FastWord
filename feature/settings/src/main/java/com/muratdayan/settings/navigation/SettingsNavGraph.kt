package com.muratdayan.settings.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.muratdayan.navigation.Screen
import com.muratdayan.settings.presentation.SettingsScreenRoot

fun NavGraphBuilder.settingsNavGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = Screen.SettingsScreenRoute.route
    ) {
        SettingsScreenRoot(
            modifier = modifier,
        )

    }
}