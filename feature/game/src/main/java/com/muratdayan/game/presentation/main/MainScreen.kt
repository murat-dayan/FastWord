package com.muratdayan.game.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme

@Composable
internal fun MainScreenRoot(
    modifier: Modifier= Modifier
){

    MainScreen(
        modifier = modifier
    )
}

@Composable
private fun MainScreen(
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(Dimensions.paddingSmall)
    ){
        FastWordTextComp(
            text = "Main Screen"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    FastWordTheme {
        MainScreen()
    }
}