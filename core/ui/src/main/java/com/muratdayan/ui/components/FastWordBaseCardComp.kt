package com.muratdayan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun FastWordBaseCardComp(
    modifier: Modifier = Modifier,
    onClick : () -> Unit = {},
    height: Int = 70,
    containerColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable () -> Unit,

) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(height.dp)
            .clickable {
                onClick()
            }
            .padding(bottom = Dimensions.spacingSmall),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimensions.paddingSmall),
            contentAlignment = Alignment.Center
        ){
            content()
        }
    }
}

@Preview
@Composable
private fun FastWordBaseCardCompPreview() {
    FastWordTheme {
        FastWordBaseCardComp(
            content = {
                Text("test")
            }
        )
    }
}
