package com.muratdayan.game.presentation.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.game.R
import com.muratdayan.game.presentation.start.components.CircularCountDownComp
import com.muratdayan.ui.components.FastWordBarHeaderComp
import com.muratdayan.ui.components.FastWordBaseCardComp
import com.muratdayan.ui.components.FastWordProfileImageComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme
import kotlinx.coroutines.delay

@Composable
fun StartScreenRoot(
    modifier: Modifier = Modifier,

    ) {
    StartScreen(
        modifier = modifier
    )
}

@Composable
private fun StartScreen(
    modifier: Modifier = Modifier,
) {

    var count by remember { mutableStateOf(10) }

    LaunchedEffect(key1 = count) {
        if (count > 0) {
            delay(1000)
            count--
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(Dimensions.paddingSmall),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacingExtraLarge)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
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

            Row {
                FastWordBarHeaderComp(
                    currentEnergy = 20,
                    maxEnergy = 10,
                    coinValue = 10,
                    emeraldValue = 40,
                )
            }
        }


        FastWordTextComp(
            text = "Round 1",
            fontWeight = FontWeight.Bold,
            fontSize = Dimensions.textSizeTitle
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FastWordProfileImageComp(
                imagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar),
                size = 60
            )

            FastWordTextComp(
                text = "0",
                fontWeight = FontWeight.Bold,
                fontSize = Dimensions.textSizeTitle
            )

            FastWordTextComp(
                text = "-",
                fontWeight = FontWeight.Bold,
                fontSize = Dimensions.textSizeTitle
            )

            FastWordTextComp(
                text = "0",
                fontWeight = FontWeight.Bold,
                fontSize = Dimensions.textSizeTitle
            )

            FastWordProfileImageComp(
                imagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar),
                size = 60
            )
        }

        CircularCountDownComp(
            currentValue = count,
            maxValue = 10,
            modifier = Modifier
                .padding(Dimensions.paddingSmall)
        )

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(Dimensions.spacingSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            FastWordTextComp(
                text = "Next Question",
                modifier = Modifier
                    .fillMaxWidth()
            )

            FastWordBaseCardComp(
                containerColor = MaterialTheme.colorScheme.scrim.copy(0.8f),
                modifier = Modifier
                    .height(50.dp)
            ) {
                FastWordTextComp(
                    text = "Question",
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimensions.spacingSmall)
            ) {
                FastWordBaseCardComp(
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        FastWordTextComp(
                            text = "Change \n Question",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = Dimensions.textSizeSmall,
                            fontWeight = FontWeight.Bold
                        )

                        FastWordBaseCardComp(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier
                                .height(40.dp)
                                .width(80.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                FastWordTextComp(
                                    text = "100"
                                )
                                Icon(
                                    painter = painterResource(com.muratdayan.ui.R.drawable.ic_coin),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(20.dp),
                                    tint = Color.Unspecified
                                )
                            }
                        }
                    }
                }

                FastWordBaseCardComp(
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    FastWordTextComp(
                        text = "Accept Question",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = Dimensions.textSizeSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

    }
}

@Preview
@Composable
private fun StartScreenPreview() {
    FastWordTheme {
        StartScreen()
    }
}