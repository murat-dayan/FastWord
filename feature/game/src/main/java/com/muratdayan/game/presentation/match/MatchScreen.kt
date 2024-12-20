package com.muratdayan.game.presentation.match

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.foodrecipecomposemvi.common.collectWithLifecycle
import com.muratdayan.game.R
import com.muratdayan.ui.components.FastWordProfileImageComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun MatchScreenRoot(
    modifier: Modifier = Modifier,
    matchViewModel: MatchViewModel,
    navigateToStartScreen: () -> Unit
) {
    val uiState = matchViewModel.uiState.collectAsStateWithLifecycle()
    val uiEffect = matchViewModel.uiEffect

    MatchScreen(
        modifier = modifier,
        uiState = uiState.value,
        uiEffect = uiEffect,
        onAction = matchViewModel::onAction,
        navigateToStartScreen = navigateToStartScreen
    )
}

@Composable
private fun MatchScreen(
    modifier: Modifier = Modifier,
    uiState: MatchContract.UiState,
    uiEffect: Flow<MatchContract.UiEffect>,
    onAction: (MatchContract.UiAction) -> Unit,
    navigateToStartScreen: () -> Unit
) {

    uiEffect.collectWithLifecycle { effect ->
        when (effect) {
            MatchContract.UiEffect.NavigateToStartScreen -> {
                navigateToStartScreen()
            }
        }
    }

    LaunchedEffect(true) {
        // İlk olarak userInfo'yu al
        onAction(MatchContract.UiAction.GetUserInfo)
    }

    LaunchedEffect(uiState.userInfo) {
        uiState.userInfo?.let {
            onAction(MatchContract.UiAction.FindOrCreateRoom(it.id))
        }
    }

    Box (
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(Dimensions.paddingSmall),

    ){

        IconButton(
            onClick = {}
        ) {
            Icon(
                painter = painterResource(com.muratdayan.ui.R.drawable.ic_back),
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.background
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)
            ){
                FastWordProfileImageComp(
                    imageUri = uiState.userInfo?.avatar_uri,
                    size = 100
                )
                FastWordTextComp(
                    text = uiState.userInfo?.user_name ?: "",
                )
            }

            FastWordTextComp(
                text = "VS.",
                fontWeight = FontWeight.Bold,
                fontSize = Dimensions.textSizeTitle
            )

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)
            ){
                FastWordProfileImageComp(
                    imagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar),
                    size = 100
                )
                FastWordTextComp(
                    text = if (uiState.isLoading) "Waiting..." else "",
                    color = MaterialTheme.colorScheme.secondaryContainer
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MatchScreenPreview() {
    FastWordTheme {
        MatchScreen(
            modifier = Modifier,
            onAction = {},
            uiState = MatchContract.UiState(),
            uiEffect = emptyFlow(),
            navigateToStartScreen = {}
        )
    }
}