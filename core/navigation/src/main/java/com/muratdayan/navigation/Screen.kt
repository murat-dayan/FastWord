package com.muratdayan.navigation

sealed class Screen(val route:String) {

    data object SignInScreenRoute: Screen("sign_in_screen")
    data object MainScreenRoute: Screen("main_screen")
    data object ShopScreenRoute: Screen("shop_screen")
    data object LeaderBoardScreenRoute: Screen("leaderboard_screen")
    data object FriendsScreenRoute: Screen("friends_screen")

}