package com.muratdayan.game.presentation.play

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.game.R
import com.muratdayan.game.presentation.play.components.PlayBoostHelperComp
import com.muratdayan.game.presentation.play.components.PlayTimerComp
import com.muratdayan.ui.components.FastWordBarHeaderComp
import com.muratdayan.ui.components.FastWordBaseCardComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun PlayScreenRoot(
    modifier: Modifier = Modifier,
    playScreenViewModel: PlayScreenViewModel
) {

    val uiState = playScreenViewModel.uiState.collectAsStateWithLifecycle()
    val uiEffect = playScreenViewModel.uiEffect

    PlayScreen(
        modifier = modifier,
        uiState = uiState.value,
        uiEffect = uiEffect,
        onAction = playScreenViewModel::onAction
    )
}


@Composable
private fun PlayScreen(
    modifier: Modifier = Modifier,
    uiState: PlayScreenContract.UiState,
    uiEffect: Flow<PlayScreenContract.UiEffect>,
    onAction: (PlayScreenContract.UiAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(Dimensions.paddingSmall),
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)
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

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            FastWordBaseCardComp(
                containerColor = MaterialTheme.colorScheme.scrim.copy(0.8f),
                height = 30
            ) {
                FastWordTextComp(
                    text = "Question",
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(Dimensions.paddingSmall)
                ) {

                }

                PlayTimerComp(
                    totalTime = 10,
                    imageUri = "",
                    username = "username"
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlayBoostHelperComp(
                modifier = Modifier
                    .weight(1f),
                boostName = "Joker",
                boostIcon = painterResource(R.drawable.ic_joker_tick),
                boostAmount = 60,
                onClick = {
                }
            )
            Spacer(modifier = Modifier.width(Dimensions.spacingSmall))
            PlayBoostHelperComp(
                modifier = Modifier
                    .weight(1f),
                boostName = "Time",
                boostIcon = painterResource(R.drawable.ic_hourglass),
                boostAmount = 60,
                onClick = {
                }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                value = "",
                onValueChange = {},
                label = {
                    FastWordTextComp(
                        text = "Type Your Answer",
                        color = MaterialTheme.colorScheme.scrim.copy(0.2f)
                    )
                },
                modifier = Modifier
                    .height(40.dp)
                    .background(MaterialTheme.colorScheme.background)

            )

            FastWordBaseCardComp(
                modifier = Modifier,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                height = 50
            ) {
                FastWordTextComp(
                    text = "Answer",
                    color = MaterialTheme.colorScheme.scrim
                )
            }
        }


    }
}


@Preview
@Composable
private fun PlayScreenPreview() {
    FastWordTheme {
        PlayScreen(
            uiState = PlayScreenContract.UiState(),
            uiEffect = emptyFlow(),
            onAction = {}
        )
    }
}