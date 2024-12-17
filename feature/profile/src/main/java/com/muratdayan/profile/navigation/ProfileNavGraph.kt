package com.muratdayan.profile.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.muratdayan.navigation.Screen
import com.muratdayan.profile.presentation.profile.ProfileScreenRoot
import com.muratdayan.profile.presentation.profile.ProfileViewModel

fun NavGraphBuilder.profileNavGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = Screen.ProfileScreenRoute.route
    ) {
        val profileViewModel = hiltViewModel<ProfileViewModel>()
        ProfileScreenRoot(
            modifier = modifier,
            profileViewModel = profileViewModel
        )

    }
}