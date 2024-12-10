package com.muratdayan.common

import android.content.Context
import android.content.SharedPreferences

class LoginStateManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("login_state", Context.MODE_PRIVATE)

    private val isLoggedInKey = "is_logged_in"

    fun setLoggedIn(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean(isLoggedInKey, isLoggedIn).apply()
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(isLoggedInKey, false)
    }
}