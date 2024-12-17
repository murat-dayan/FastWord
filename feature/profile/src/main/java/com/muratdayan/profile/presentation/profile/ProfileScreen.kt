package com.muratdayan.profile.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.profile.R
import com.muratdayan.profile.presentation.profile.components.CircularProgressComp
import com.muratdayan.profile.presentation.profile.components.ProfileImageWithQuestionMark
import com.muratdayan.profile.presentation.profile.components.ProfileStatsTitleComp
import com.muratdayan.profile.presentation.profile.util.UserType
import com.muratdayan.ui.components.FastWordBarHeaderComp
import com.muratdayan.ui.components.FastWordBaseCardComp
import com.muratdayan.ui.components.FastWordButtonComp
import com.muratdayan.ui.components.FastWordProfileImageComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun ProfileScreenRoot(
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel
) {
    val uiState = profileViewModel.uiState.collectAsStateWithLifecycle()
    val uiEffect = profileViewModel.uiEffect

    ProfileScreen(
        modifier = modifier,
        uiState = uiState.value,
        uiEffect = uiEffect,
        onAction = profileViewModel::onAction
    )
}

@Composable
private fun ProfileScreen(
    modifier: Modifier = Modifier,
    uiState: ProfileContract.UiState,
    uiEffect: Flow<ProfileContract.UiEffect>,
    onAction: (ProfileContract.UiAction) -> Unit
) {

    var userType by remember { mutableStateOf(UserType.CURRENT) }

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(Dimensions.paddingSmall),
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
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

            when(userType){
                UserType.CURRENT ->{
                    Row {
                        FastWordBarHeaderComp(
                            currentEnergy = 1,
                            maxEnergy = 10,
                            coinValue = 0,
                            emeraldValue = 0
                        )
                    }
                }
                UserType.FRIEND -> {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More",
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                }
                UserType.OTHER -> {}
            }
        }

        when(userType){
            UserType.CURRENT -> {
                ProfileImageWithQuestionMark()
            }
            UserType.FRIEND -> {
                FastWordProfileImageComp(
                    imagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar)
                )
            }
            UserType.OTHER -> {}
        }

        FastWordTextComp(
            text = "Murat",
            fontWeight = FontWeight.Bold
        )

        when(userType){
            UserType.CURRENT -> {
                FastWordBaseCardComp (
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(0.8f),
                    height = 50
                ){
                    FastWordTextComp(
                        text = "Invite Friends",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            UserType.FRIEND -> {
                FastWordButtonComp(
                    modifier = Modifier
                        .width(200.dp),
                    text = "Play Now",
                    iconText = "3",
                    icon = com.muratdayan.ui.R.drawable.ic_flash,
                )
            }
            UserType.OTHER -> {}
        }

        ProfileStatsTitleComp(
            iconPainter = painterResource(com.muratdayan.ui.R.drawable.ic_crown),
            statTitleText = "Wins",
            iconTint = MaterialTheme.colorScheme.secondaryContainer
        )

        FastWordBaseCardComp(
            height = 120,
            containerColor = MaterialTheme.colorScheme.scrim.copy(0.8f)
        ) {
            CircularProgressComp(
                percentage = 80f
            )
        }

        ProfileStatsTitleComp(
            iconPainter = painterResource(R.drawable.ic_game),
            statTitleText = "Games",
            iconTint = Color.Unspecified
        )

        FastWordBaseCardComp(
            containerColor = MaterialTheme.colorScheme.scrim.copy(0.8f)
        ) {
            FastWordTextComp(
                text = "514",
                fontWeight = FontWeight.Bold,
                fontSize = Dimensions.textSizeLarge
            )
        }

        ProfileStatsTitleComp(
            iconPainter = painterResource(R.drawable.ic_correct),
            statTitleText = "Correct Answers",
            iconTint = Color.Unspecified
        )

        FastWordBaseCardComp(
            containerColor = MaterialTheme.colorScheme.scrim.copy(0.8f)
        ) {
            FastWordTextComp(
                text = "2114",
                fontWeight = FontWeight.Bold,
                fontSize = Dimensions.textSizeLarge
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    FastWordTheme {
        ProfileScreen(
            uiState = ProfileContract.UiState(),
            uiEffect = emptyFlow(),
            onAction = {}
        
        )
    }
}
