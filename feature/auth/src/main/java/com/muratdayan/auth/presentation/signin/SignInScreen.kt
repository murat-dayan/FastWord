package com.muratdayan.auth.presentation.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muratdayan.auth.R
import com.muratdayan.auth.presentation.components.AuthButtonComp
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.ui.theme.FastWordTheme

@Composable
internal fun SignInScreenRoot(
    modifier: Modifier = Modifier
){
    SignInScreen(modifier = modifier)
}

@Composable
private fun SignInScreen(
    modifier: Modifier = Modifier
){

    Column (
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(Dimensions.paddingSmall)
            .fillMaxSize()
            ,
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacingLarge, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(com.muratdayan.ui.R.drawable.fastwordlogo),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
        )

        AuthButtonComp(
            text = stringResource(R.string.facebook_sign),
            icon = R.drawable.ic_facebook,
            textColor = MaterialTheme.colorScheme.secondary
        )

        AuthButtonComp(
            text = stringResource(R.string.guest_sign),
            icon = R.drawable.ic_guest,
            textColor = MaterialTheme.colorScheme.primary
        )
    }


}

@Composable
@Preview(showBackground = true)
fun SignInScreenPreview(){
    FastWordTheme {
        SignInScreen()
    }
}