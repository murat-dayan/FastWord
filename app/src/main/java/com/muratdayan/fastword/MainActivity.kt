package com.muratdayan.fastword

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.muratdayan.auth.navigation.authNavGraph
import com.muratdayan.common.LoginStateManager
import com.muratdayan.friends.navigation.friendsNavGraph
import com.muratdayan.game.navigation.gameNavGraph
import com.muratdayan.leaderboard.navigation.leaderBoardNavGraph
import com.muratdayan.navigation.NavigationGraph
import com.muratdayan.navigation.Screen
import com.muratdayan.profile.navigation.profileNavGraph
import com.muratdayan.settings.navigation.settingsNavGraph
import com.muratdayan.shop.navigation.shopNavGraph
import com.muratdayan.ui.theme.FastWordTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var loginStateManager: LoginStateManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FastWordTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationGraph(
                        navHostController = navController,
                        isSignedIn = loginStateManager.isLoggedIn(),
                        modifier = Modifier.padding(innerPadding),
                        authNavGraph = {
                            authNavGraph(
                                modifier = it,
                                navigateToGame = {
                                    navController.navigate(Screen.MainScreenRoute.route)
                                }
                            )
                        },
                        gameNavGraph = {
                            gameNavGraph(
                                modifier = it,
                                navigateToShop = {
                                    navController.navigate(Screen.ShopScreenRoute.route)
                                },
                                navigateToSettings = {
                                    navController.navigate(Screen.SettingsScreenRoute.route)
                                },
                                navigateToFriends = {
                                    navController.navigate(Screen.FriendsScreenRoute.route)
                                },
                                navigateToLeaderBoard = {
                                    navController.navigate(Screen.LeaderBoardScreenRoute.route)
                                }
                            )
                        },
                        shopNavGraph = {
                            shopNavGraph(modifier = it)
                        },
                        leaderBoardNavGraph = {
                            leaderBoardNavGraph(modifier = it)
                        },
                        friendsNavGraph = {
                            friendsNavGraph(modifier = it)
                        },
                        settingsNavGraph = {
                            settingsNavGraph(
                                modifier = it,
                                navigateToSignInScreen = {
                                    navController.navigate(Screen.SignInScreenRoute.route)
                                }
                            )
                        },
                        profileNavGraph = {
                            profileNavGraph(modifier = it)
                        }
                    )
                }
            }
        }
    }
}

