package com.muratdayan.profile.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.ui.components.FastWordButtonComp
import com.muratdayan.ui.components.FastWordProfileImageComp
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun ProfilePhotoChooseDialog(
    showDialog: Boolean = false,
    onDismiss: () -> Unit = {},
    modifier: Modifier = Modifier,
    mansPhotos: List<String>? = null,
    girlsPhotos: List<String>? = null,
    onClickOkayBtn: () -> Unit = {}
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(Dimensions.paddingLarge)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.9f)
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = MaterialTheme.shapes.medium
                            )
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)
                        ) {
                            items(15) {
                                FastWordProfileImageComp(
                                    imagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar),
                                    size = 60
                                )
                            }
                        }

                        LazyColumn(
                            modifier = Modifier
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)
                        ) {
                            items(15) {
                                FastWordProfileImageComp(
                                    imagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar),
                                    size = 60
                                )
                            }
                        }
                    }

                    FastWordButtonComp(
                        modifier = Modifier
                            .weight(0.1f),
                        text = "OKAY",
                        onClick = {onClickOkayBtn()},
                        textAlignment = TextAlign.Center,
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        textColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                }
                FastWordProfileImageComp(
                    size = 60,
                    imagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = (-60).dp)
                )
            }


        }
    }

}

@Preview
@Composable
private fun ProfilePhotoChooseDialogPreview() {
    FastWordTheme {
        ProfilePhotoChooseDialog(
            showDialog = true
        )
    }
}