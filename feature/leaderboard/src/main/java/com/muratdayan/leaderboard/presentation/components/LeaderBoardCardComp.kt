package com.muratdayan.leaderboard.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.ui.components.FastWordBaseCardComp
import com.muratdayan.ui.components.FastWordProfileImageComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun LeaderBoardCardComp(
    modifier: Modifier = Modifier,
    userImagePainter: Painter = painterResource(com.muratdayan.ui.R.drawable.avatar),
    userName:String,
    iconOrderPainter: Painter?=null,
    onClickCard:()->Unit = {},
    onClickImage:()->Unit = {},
    iconTint: Color? = null,
    scoreText:String,
    orderText:String
){

    FastWordBaseCardComp(
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClickCard,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Row (
            modifier = modifier
                .fillMaxSize()
                .padding(Dimensions.paddingSmall),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row (
                modifier= Modifier
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)
            ){

                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ){
                    if (iconOrderPainter!=null){
                        Icon(
                            painter = iconOrderPainter,
                            contentDescription = null,
                            tint = iconTint ?: MaterialTheme.colorScheme.background,
                            modifier = Modifier.size(20.dp)
                        )
                    }else{
                        FastWordTextComp(
                            text = orderText,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }

                FastWordProfileImageComp(
                    imagePainter = userImagePainter,
                    size = 40,
                    onClick = onClickImage
                )
                FastWordTextComp(
                    text = userName,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }

            FastWordTextComp(
                text = scoreText,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }


    }
}

@Preview
@Composable
private fun LeaderBoardCardCompPreview(){
    FastWordTheme {
        LeaderBoardCardComp(
            userName = "Murat",
            scoreText = "100",
            orderText = "1"
        )
    }
}