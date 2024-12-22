package com.muratdayan.navigation

sealed class Screen(val route:String) {

    data object SignInScreenRoute: Screen("sign_in_screen")
    data object MainScreenRoute: Screen("main_screen")
    data object MatchScreenRoute: Screen("match_screen")
    data object StartScreenRoute: Screen("start_screen/{roomId}"){
        fun withroomId(roomId:String) = "start_screen/$roomId"
    }
    data object PlayScreenRoute: Screen("play_screen")
    data object ShopScreenRoute: Screen("shop_screen")
    data object LeaderBoardScreenRoute: Screen("leaderboard_screen")
    data object FriendsScreenRoute: Screen("friends_screen")
    data object SettingsScreenRoute: Screen("settings_screen")
    data object ProfileScreenRoute: Screen("profile_screen/{userId}"){
        fun withUserId(userId:String) = "profile_screen/$userId"
    }

}