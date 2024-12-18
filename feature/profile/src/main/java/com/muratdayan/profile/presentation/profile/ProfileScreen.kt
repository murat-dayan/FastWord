package com.muratdayan.profile.presentation.profile

import android.util.Log
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.profile.R
import com.muratdayan.profile.presentation.profile.components.CircularProgressComp
import com.muratdayan.profile.presentation.profile.components.ProfileImageWithQuestionMark
import com.muratdayan.profile.presentation.profile.components.ProfilePhotoChooseDialog
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
    profileViewModel: ProfileViewModel,
    userId: String? = null
) {
    val uiState = profileViewModel.uiState.collectAsStateWithLifecycle()
    val uiEffect = profileViewModel.uiEffect

    ProfileScreen(
        modifier = modifier,
        uiState = uiState.value,
        uiEffect = uiEffect,
        onAction = profileViewModel::onAction,
        userId = userId ?: ""
    )
}

@Composable
private fun ProfileScreen(
    modifier: Modifier = Modifier,
    userId: String,
    uiState: ProfileContract.UiState,
    uiEffect: Flow<ProfileContract.UiEffect>,
    onAction: (ProfileContract.UiAction) -> Unit
) {

    Log.d("ProfileScreen", "ProfileScreen: $userId")

    var showProfileDialog by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        if (userId.isNotEmpty()) {
            onAction(ProfileContract.UiAction.CheckUserType(userId))
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(Dimensions.paddingSmall),
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium),
        horizontalAlignment = Alignment.CenterHorizontally
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

            when (uiState.userType) {
                UserType.CURRENT -> {
                    Row {
                        FastWordBarHeaderComp(
                            currentEnergy = uiState.userStats?.energy ?: 0,
                            maxEnergy = 10,
                            coinValue = uiState.userStats?.token ?: 0,
                            emeraldValue = uiState.userStats?.emerald ?: 0
                        )
                    }
                }

                UserType.FRIEND, UserType.OTHER, UserType.PENDING -> {
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

                null -> {}
            }
        }



        when (uiState.userType) {
            UserType.CURRENT -> {
                ProfileImageWithQuestionMark(
                    onClickQuestionMark = {
                        showProfileDialog = true
                    },
                    profileImagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar)
                )
            }

            UserType.FRIEND, UserType.OTHER, UserType.PENDING -> {
                FastWordProfileImageComp(
                    imagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar)
                )
            }

            null -> {}

        }

        FastWordTextComp(
            text = "Murat",
            fontWeight = FontWeight.Bold
        )

        when (uiState.userType) {
            UserType.CURRENT -> {
                FastWordBaseCardComp(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(0.8f),
                    height = 50,

                    ) {
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

            UserType.OTHER -> {
                Row(
                    modifier = Modifier
                        .padding(horizontal = Dimensions.paddingMedium),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)
                ) {
                    FastWordButtonComp(
                        modifier = Modifier
                            .weight(1f),
                        text = "Add Friend",
                        onClick = {
                            onAction(ProfileContract.UiAction.SendFriendRequest(userId))
                        },
                        containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f),
                        textColor = MaterialTheme.colorScheme.background,
                        textAlignment = TextAlign.Center
                    )

                    FastWordButtonComp(
                        modifier = Modifier
                            .weight(1f),
                        text = "Play Now",
                        iconText = "3",
                        icon = com.muratdayan.ui.R.drawable.ic_flash,
                    )
                }
            }

            UserType.PENDING -> {
                FastWordButtonComp(
                    modifier = Modifier
                        .width(200.dp),
                    text = "Play Now",
                    iconText = "3",
                    icon = com.muratdayan.ui.R.drawable.ic_flash,
                )

                FastWordTextComp(
                    text = "Friend Request Sent",
                    fontWeight = FontWeight.Bold
                )

            }

            null -> {}

        }

        ProfilePhotoChooseDialog(
            showDialog = showProfileDialog,
            onDismiss = {
                showProfileDialog = false
            },
            mansPhotos = null,
            girlsPhotos = null
        )


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
            iconPainter = painterResource(com.muratdayan.ui.R.drawable.ic_tick_yes),
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
            uiState = ProfileContract.UiState(
                userType = UserType.PENDING,
            ),
            uiEffect = emptyFlow(),
            onAction = {},
            userId = ""
        )
    }
}
