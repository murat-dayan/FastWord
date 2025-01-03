package com.muratdayan.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.muratdayan.ui.R
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun FastWordProfileImageComp(
    imagePainter: Painter?=null,
    imageUri: String? = null,
    modifier: Modifier = Modifier,
    size: Int = 100,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    onClick : ()->Unit = {}
){
    Box(
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.background)
            .clickable {
                onClick()
            }
    ) {
        if (imageUri != null) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUri)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = contentDescription,

                contentScale = contentScale,
                modifier = Modifier
                    .matchParentSize()
                    .clip(CircleShape)
            )
        }else{
            if (imagePainter != null) {
                Image(
                    painter = imagePainter,
                    contentDescription = contentDescription,
                    contentScale = contentScale,
                    modifier = Modifier
                        .matchParentSize()
                        .clip(CircleShape)
                )
            }
        }
    }
}

@Composable
@Preview()
private fun FastWordProfileImageCompPreview(){
    FastWordTheme {
        FastWordProfileImageComp(
            imagePainter = painterResource(R.drawable.avatar),
        )
    }
}