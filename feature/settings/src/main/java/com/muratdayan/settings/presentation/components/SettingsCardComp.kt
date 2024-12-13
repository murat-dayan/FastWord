package com.muratdayan.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.muratdayan.ui.components.FastWordBaseCardComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun SettingsCardComp(
    cardText : String,
    modifier: Modifier = Modifier,
    onClick : () -> Unit = {},
    secondContent : @Composable () -> Unit = {}

){
    FastWordBaseCardComp(
        onClick = onClick,
        content = {
            Row (
                modifier = modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                FastWordTextComp(
                    text = cardText,
                    color = MaterialTheme.colorScheme.primary
                )

                secondContent()
            }
        }
    )
}

@Preview
@Composable
private fun SettingsCardCompPreview() {
    FastWordTheme {
        SettingsCardComp(
            cardText = "Test"
        )
    }
}