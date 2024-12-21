package com.muratdayan.profile.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.muratdayan.navigation.Screen
import com.muratdayan.profile.presentation.profile.ProfileScreenRoot
import com.muratdayan.profile.presentation.profile.ProfileViewModel

fun NavGraphBuilder.profileNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    composable(
        route = Screen.ProfileScreenRoute.route,
        arguments = listOf(navArgument("userId"){type = NavType.StringType})
    ) {backStackEntry ->
        val profileViewModel = hiltViewModel<ProfileViewModel>()
        val userId = backStackEntry.arguments?.getString("userId")
        ProfileScreenRoot(
            modifier = modifier,
            profileViewModel = profileViewModel,
            userId = userId,
            navigateToBack = {
                navHostController.popBackStack()
            }
        )

    }
}