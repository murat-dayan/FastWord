package com.muratdayan.profile.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muratdayan.ui.components.FastWordProfileImageComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun ProfileImageWithQuestionMark(
    modifier: Modifier = Modifier,
    onClickQuestionMark: () -> Unit = {},
    profileImagePainter: Painter?= painterResource(com.muratdayan.ui.R.drawable.avatar)
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(100.dp)
    ) {

        FastWordProfileImageComp(
            imagePainter = profileImagePainter?: painterResource(com.muratdayan.ui.R.drawable.avatar),
        )


        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .clickable {
                    onClickQuestionMark()
                }
                .padding(8.dp)
                .size(24.dp)
                .background(MaterialTheme.colorScheme.scrim, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            FastWordTextComp(
                text = "?",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
private fun ProfileImageWithQuestionMarkPreview(){
    FastWordTheme {
        ProfileImageWithQuestionMark(
            profileImagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar)
        )
    }
}