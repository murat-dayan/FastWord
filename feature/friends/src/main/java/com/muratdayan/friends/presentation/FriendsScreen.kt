package com.muratdayan.friends.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.foodrecipecomposemvi.common.collectWithLifecycle
import com.muratdayan.friends.R
import com.muratdayan.friends.presentation.components.RequestCardComp
import com.muratdayan.friends.util.FriendsListType
import com.muratdayan.ui.components.FastWordAdvertHeaderComp
import com.muratdayan.ui.components.FastWordBarHeaderComp
import com.muratdayan.ui.components.FastWordBaseCardComp
import com.muratdayan.ui.components.FastWordButtonComp
import com.muratdayan.ui.components.FastWordGameCardComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme
import com.muratdayan.ui.theme.extendedColors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun FriendsScreenRoot(
    modifier: Modifier = Modifier,
    friendsViewModel: FriendsViewModel,
    navigateToBack: () -> Unit
) {

    val uiState = friendsViewModel.uiState.collectAsStateWithLifecycle()
    val uiEffect = friendsViewModel.uiEffect

    FriendsScreen(
        modifier = modifier,
        uiState = uiState.value,
        uiEffect = uiEffect,
        onAction = friendsViewModel::onAction,
        navigateToBack = navigateToBack
    )
}

@Composable
private fun FriendsScreen(
    modifier: Modifier = Modifier,
    uiState: FriendsContract.UiState,
    uiEffect: Flow<FriendsContract.UiEffect>,
    onAction: (FriendsContract.UiAction) -> Unit,
    navigateToBack: () -> Unit
) {

    uiEffect.collectWithLifecycle { effect ->
        when (effect) {
            FriendsContract.UiEffect.NavigateToProfileScreen -> {}
            FriendsContract.UiEffect.NavigateToShopScreen -> {}
            FriendsContract.UiEffect.NavigateToBack -> {
                navigateToBack()
            }
        }
    }

    var selectedListTitleIndex by remember { mutableStateOf(FriendsListType.MY_FRIENDS) }

    LaunchedEffect(key1 = true) {
        onAction(FriendsContract.UiAction.GetUserStats)
        onAction(FriendsContract.UiAction.GetFriends)
        onAction(FriendsContract.UiAction.GetPendingFriends)
    }

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
                onClick = {
                    onAction(FriendsContract.UiAction.NavigateToBack)
                }
            ) {
                Icon(
                    painter = painterResource(com.muratdayan.ui.R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.background
                )
            }

            Row {
                FastWordBarHeaderComp(
                    currentEnergy = uiState.userStats?.energy ?: 0,
                    maxEnergy = 10,
                    coinValue = uiState.userStats?.token ?: 0,
                    emeraldValue = uiState.userStats?.emerald ?: 0,
                )
            }
        }

        // Advert Header
        FastWordAdvertHeaderComp()

        FastWordButtonComp(
            text = "Invite Friends",
            textColor = MaterialTheme.colorScheme.background,
            onClick = {},
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(0.7f),
            iconTint = MaterialTheme.colorScheme.primary,
            textAlignment = TextAlign.Center,
            isLoading = uiState.isLoading
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            FastWordBaseCardComp(
                modifier = Modifier
                    .weight(1f),
                height = 50,
                onClick = {
                    selectedListTitleIndex = FriendsListType.MY_FRIENDS
                    onAction(FriendsContract.UiAction.GetFriends)
                },
                containerColor = if (selectedListTitleIndex == FriendsListType.MY_FRIENDS)
                    MaterialTheme.colorScheme.background
                else MaterialTheme.extendedColors.customBlue.colorContainer.copy(0.8f)
            ) {
                FastWordTextComp(
                    text = "My Friends",
                    fontWeight = FontWeight.Bold,
                    color = if (selectedListTitleIndex == FriendsListType.MY_FRIENDS)
                        MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.background
                )
            }
            BadgedBox(
                modifier = Modifier
                    .weight(1f),
                badge = if (uiState.pendingFriends.isNotEmpty()) {
                    {
                        Badge(
                            containerColor = MaterialTheme.colorScheme.error,
                            contentColor = MaterialTheme.colorScheme.background,
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.TopEnd)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_alarm),
                                contentDescription = "Request Icon",
                                tint = MaterialTheme.colorScheme.background
                            )
                        }
                    }
                } else {
                    {}
                }
            ) {
                FastWordBaseCardComp(
                    modifier = Modifier,
                    height = 50,
                    onClick = {
                        selectedListTitleIndex = FriendsListType.REQUESTS
                        onAction(FriendsContract.UiAction.GetPendingFriends)
                    },
                    containerColor = if (selectedListTitleIndex == FriendsListType.REQUESTS)
                        MaterialTheme.colorScheme.background
                    else MaterialTheme.extendedColors.customBlue.colorContainer.copy(0.8f)
                ) {
                    FastWordTextComp(
                        text = "Requests",
                        fontWeight = FontWeight.Bold,
                        color = if (selectedListTitleIndex == FriendsListType.REQUESTS)
                            MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.background
                    )
                }


            }
        }
        LazyColumn {
            if (selectedListTitleIndex == FriendsListType.MY_FRIENDS) {
                uiState.friends?.let { friendsList ->
                    items(friendsList) { friend ->
                        FastWordGameCardComp(
                            name = friend.user.user_name,
                            imagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar),
                        )
                    }
                }
            }else{
                if (uiState.pendingFriends.isNotEmpty()){
                    items(uiState.pendingFriends) { pendingFriend ->
                        RequestCardComp(
                            userName = pendingFriend.user.user_name,
                            imagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar),
                            onClickAccept = {
                                onAction(FriendsContract.UiAction.UpdateFriendStatus(
                                    senderId = pendingFriend.user.id,
                                    status = "accepted"
                                ))
                            },
                            onClickReject = {
                                onAction(FriendsContract.UiAction.UpdateFriendStatus(
                                    senderId = pendingFriend.user.id,
                                    status = "rejected"
                                ))
                            },
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FriendsScreenPreview() {
    FastWordTheme {
        FriendsScreen(
            uiState = FriendsContract.UiState(),
            uiEffect = emptyFlow(),
            onAction = {},
            navigateToBack = {}
        )
    }
}