package com.muratdayan.settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.settings.R
import com.muratdayan.settings.presentation.components.SettingsCardComp
import com.muratdayan.ui.components.FastWordBaseCardComp
import com.muratdayan.ui.components.FastWordButtonComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun SettingsScreenRoot(
    modifier: Modifier = Modifier,
) {
    SettingsScreen(
        modifier = modifier
    )
}

@Composable
private fun SettingsScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(Dimensions.paddingSmall),
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium),

    ) {
        IconButton(
            onClick = {}
        ) {
            Icon(
                painter = painterResource(com.muratdayan.ui.R.drawable.ic_back),
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.background
            )
        }

        FastWordTextComp(
            text = "Settings",
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
            fontWeight = FontWeight.Bold,
            fontSize = Dimensions.textSizeLarge
        )

        SettingsCardComp(
            cardText = "Vibration",
            onClick = {},
            secondContent = {
                Switch(
                    checked = false,
                    onCheckedChange = {}
                )
            }
        )

        SettingsCardComp(
            cardText = "Language",
            onClick = {},
            secondContent = {
                FastWordTextComp(
                    text = "English",
                    color = MaterialTheme.colorScheme.primary,
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline
                    )
                )
            }
        )

        FastWordTextComp(
            text = "Other",
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
            fontWeight = FontWeight.Bold,
            fontSize = Dimensions.textSizeLarge
        )

        SettingsCardComp(
            cardText = "Terms Of Use"
        )

        SettingsCardComp(
            cardText = "FAQ"
        )

        SettingsCardComp(
            cardText = "Restore Purchases",
            secondContent = {
                FastWordButtonComp(
                    modifier = Modifier
                        .width(80.dp),
                    icon = R.drawable.ic_recycle
                )
            }
        )

        SettingsCardComp(
            cardText = "Exit",
            secondContent = {
                FastWordButtonComp(
                    modifier = Modifier
                        .width(80.dp),
                    icon = R.drawable.ic_exit
                )
            }
        )

        FastWordTextComp(
            "Remove Account",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.End,
            color = MaterialTheme.colorScheme.background,
            style = TextStyle(
                textDecoration = TextDecoration.Underline
            )
        )

        FastWordTextComp(
            text = "Questions",
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
            fontWeight = FontWeight.Bold,
            fontSize = Dimensions.textSizeLarge
        )

        SettingsCardComp(
            cardText = "Choose Language",
            onClick = {}
        )


    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    FastWordTheme {
        SettingsScreen()
    }
}