package com.muratdayan.auth.presentation.signin

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muratdayan.auth.R
import com.muratdayan.auth.presentation.components.AuthButtonComp
import com.muratdayan.auth.utils.openCustomTab
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.foodrecipecomposemvi.common.collectWithLifecycle
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun SignInScreenRoot(
    modifier: Modifier = Modifier,
    signInViewModel: SignInViewModel,
    navigateToGame: () -> Unit
){
    val uiState = signInViewModel.uiState.collectAsStateWithLifecycle()
    val uiEffect = signInViewModel.uiEffect
    SignInScreen(
        modifier = modifier,
        uiState = uiState.value,
        uiEffect = uiEffect,
        navigateToGame= navigateToGame,
        onUiAction = signInViewModel::onAction
    )
}

@Composable
private fun SignInScreen(
    modifier: Modifier = Modifier,
    uiState: SignInContract.UiState,
    uiEffect: Flow<SignInContract.UiEffect>,
    onUiAction: (SignInContract.UiAction) ->Unit,
    navigateToGame: ()->Unit
){

    val context = LocalContext.current

    uiEffect.collectWithLifecycle { effect->
        when(effect){
            SignInContract.UiEffect.NavigateToMainScreen -> {
                navigateToGame()
            }

            is SignInContract.UiEffect.OpenCustomTab -> {
                openCustomTab(context, effect.uri)
                onUiAction(SignInContract.UiAction.ClearFacebookUrl)
            }
        }
    }


    Column (
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(Dimensions.paddingSmall)
            .fillMaxSize()
            ,
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacingLarge, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FastWordTextComp(
            text = stringResource(R.string.sign_in_title),
            color = MaterialTheme.colorScheme.background,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = Dimensions.textSizeXLarge
        )
        Image(
            painter = painterResource(com.muratdayan.ui.R.drawable.fastwordlogo),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
        )

        AuthButtonComp(
            text = stringResource(R.string.facebook_sign),
            icon = R.drawable.ic_facebook,
            textColor = MaterialTheme.colorScheme.secondary,
            onClick = {
                onUiAction(SignInContract.UiAction.FacebookSignIn)
            },
        )

        AuthButtonComp(
            text = stringResource(R.string.guest_sign),
            icon = R.drawable.ic_guest,
            textColor = MaterialTheme.colorScheme.primary,
            onClick = {
                onUiAction(SignInContract.UiAction.GuestSignIn)
            },
            isLoading = uiState.isLoading
        )

    }


}



@Composable
@Preview(showBackground = true)
fun SignInScreenPreview(){
    FastWordTheme {
        SignInScreen(
            uiState = SignInContract.UiState(),
            uiEffect = emptyFlow(),
            onUiAction = {},
            navigateToGame = {}
        )
    }
}