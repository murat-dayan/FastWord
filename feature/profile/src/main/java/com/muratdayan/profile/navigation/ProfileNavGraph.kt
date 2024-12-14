package com.muratdayan.profile.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.muratdayan.navigation.Screen
import com.muratdayan.profile.presentation.profile.ProfileScreenRoot

fun NavGraphBuilder.profileNavGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = Screen.ProfileScreenRoute.route
    ) {
        ProfileScreenRoot(
            modifier = modifier,
        )

    }
}