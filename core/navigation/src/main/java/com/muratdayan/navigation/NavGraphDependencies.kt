package com.muratdayan.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder

data class NavGraphDependencies(
    val authNavGraph : (NavGraphBuilder.(Modifier)->Unit)?,
    val gameNavGraph: (NavGraphBuilder.(modifier: Modifier)->Unit)?,
    val shopNavGraph: (NavGraphBuilder.(modifier: Modifier)->Unit)?,
    val leaderBoardNavGraph: (NavGraphBuilder.(modifier: Modifier)->Unit)?,
    val friendsNavGraph: (NavGraphBuilder.(modifier: Modifier)->Unit)?,
    val settingsNavGraph: (NavGraphBuilder.(modifier: Modifier)->Unit)?,
    val profileNavGraph: (NavGraphBuilder.(modifier: Modifier)->Unit)?
)
