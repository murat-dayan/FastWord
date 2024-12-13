package com.muratdayan.friends.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun FriendsScreenRoot(
    modifier: Modifier = Modifier,
) {
    FriendsScreen(
        modifier = modifier
    )
}

@Composable
private fun FriendsScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        FastWordTextComp(
            text = "Friends Screen"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FriendsScreenPreview() {
    FastWordTheme {
        FriendsScreen()
    }
}