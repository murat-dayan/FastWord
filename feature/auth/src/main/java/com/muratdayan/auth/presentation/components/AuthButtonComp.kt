package com.muratdayan.auth.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muratdayan.auth.R
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun AuthButtonComp(
    text: String = "Sign In",
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    textColor: Color = MaterialTheme.colorScheme.primary
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .clip(MaterialTheme.shapes.large)
            .padding(Dimensions.paddingSmall)
            .clickable { onClick() }
            ,

        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(30.dp),
            tint = Color.Unspecified
        )

        FastWordTextComp(
            text = text,
            color = textColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = Dimensions.textSizeLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AuthButtonCompPreview() {
    FastWordTheme {
        AuthButtonComp(
            icon = R.drawable.ic_guest
        )
    }
}