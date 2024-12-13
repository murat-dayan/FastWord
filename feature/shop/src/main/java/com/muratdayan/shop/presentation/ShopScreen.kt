package com.muratdayan.shop.presentation

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
fun ShopScreenRoot(
    modifier: Modifier = Modifier,
){
    ShopScreen(
        modifier = modifier
    )
}

@Composable
private fun ShopScreen(
    modifier: Modifier = Modifier,
){
    Column (
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ){
        FastWordTextComp(
            text = "Shop Screen"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ShopScreenPreview() {
    FastWordTheme {
        ShopScreen()
    }
}