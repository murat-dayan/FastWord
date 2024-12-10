package com.muratdayan.auth.utils

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

fun openCustomTab(context: Context, uri: String){
    val customTabIntent = CustomTabsIntent.Builder().build()
    customTabIntent.launchUrl(context, Uri.parse(uri))
}