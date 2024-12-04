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
import com.muratdayan.game.navigation.gameNavGraph
import com.muratdayan.navigation.NavigationGraph
import com.muratdayan.navigation.Screen
import com.muratdayan.ui.theme.FastWordTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FastWordTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationGraph(
                        navHostController = navController,
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
                            gameNavGraph(modifier = it)
                        }
                    )
                }
            }
        }
    }
}

