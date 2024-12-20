package com.muratdayan.game.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.domain.model.UserDataModel
import com.muratdayan.foodrecipecomposemvi.common.collectWithLifecycle
import com.muratdayan.game.presentation.main.component.FriendCardComp
import com.muratdayan.ui.components.FastWordAdvertHeaderComp
import com.muratdayan.ui.components.FastWordBarHeaderComp
import com.muratdayan.ui.components.FastWordButtonComp
import com.muratdayan.ui.components.FastWordGameCardComp
import com.muratdayan.ui.components.FastWordProfileImageComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


@Composable
internal fun MainScreenRoot(
    modifier: Modifier= Modifier,
    mainScreenViewModel: MainScreenViewModel,
    navigateToShop: () -> Unit,
    navigateToSettings: () -> Unit,
    navigateToFriends: () -> Unit,
    navigateToLeaderBoard: () -> Unit,
    navigateToProfile: (String) -> Unit,
    navigateToMatch: () -> Unit,
){
    val uiState = mainScreenViewModel.uiState.collectAsStateWithLifecycle()
    val uiEffect = mainScreenViewModel.uiEffect

    MainScreen(
        modifier = modifier,
        uiState = uiState.value,
        uiEffect = uiEffect,
        onAction = mainScreenViewModel::onAction,
        navigateToShop = navigateToShop,
        navigateToSettings = navigateToSettings,
        navigateToFriends = navigateToFriends,
        navigateToLeaderBoard = navigateToLeaderBoard,
        navigateToProfile = navigateToProfile,
        navigateToMatch = navigateToMatch
    )
}

@Composable
private fun MainScreen(
    modifier: Modifier = Modifier,
    uiState: MainScreenContract.UiState,
    uiEffect: Flow<MainScreenContract.UiEffect>,
    onAction: (MainScreenContract.UiAction) -> Unit,
    navigateToShop: () -> Unit,
    navigateToSettings: () -> Unit,
    navigateToFriends: () -> Unit,
    navigateToLeaderBoard: () -> Unit,
    navigateToProfile: (String) -> Unit,
    navigateToMatch: () -> Unit,
){

    LaunchedEffect(true) {
        onAction(MainScreenContract.UiAction.GetUserStats)
        onAction(MainScreenContract.UiAction.GetFriends)
        onAction(MainScreenContract.UiAction.GetUserInfo)
    }

    uiEffect.collectWithLifecycle { effect->
        when(effect){
            MainScreenContract.UiEffect.NavigateToMatchScreen -> {
                navigateToMatch()
            }
            MainScreenContract.UiEffect.NavigateToFriendsScreen -> {
                navigateToFriends()
            }
            MainScreenContract.UiEffect.NavigateToLeaderBoardScreen -> {
                navigateToLeaderBoard()
            }
            MainScreenContract.UiEffect.NavigateToSettingsScreen -> {
                navigateToSettings()
            }
            MainScreenContract.UiEffect.NavigateToShopScreen -> {
                navigateToShop()
            }

            is MainScreenContract.UiEffect.NavigateToProfileScreen -> {
                navigateToProfile(effect.userId)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(Dimensions.paddingSmall)
            ) {
                // Header Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    uiState.userInfo?.let {
                        FastWordProfileImageComp(
                            imageUri = uiState.userInfo.avatar_uri,
                            size = 50,
                            onClick = {
                                onAction(MainScreenContract.UiAction.GoToProfile(uiState.userInfo.id))
                            }
                        )
                        FastWordTextComp(
                            text = uiState.userInfo.user_name
                        )
                    }



                    Row{
                        FastWordBarHeaderComp(
                            currentEnergy = uiState.userStats?.energy ?:0,
                            maxEnergy = 10,
                            coinValue = uiState.userStats?.token ?:0,
                            emeraldValue = uiState.userStats?.emerald ?:0,
                            onEmeraldClick = {
                                onAction(MainScreenContract.UiAction.GoToShop)
                            },
                            onEnergyClick = {
                                onAction(MainScreenContract.UiAction.GoToShop)
                            },
                            onCoinClick = {
                                onAction(MainScreenContract.UiAction.GoToShop)
                            }
                        )
                    }
                }

                // Advert Header
                FastWordAdvertHeaderComp()

                FastWordButtonComp(
                    text = "Play Now",
                    isLoading = uiState.isLoading,
                    iconText = "3",
                    onClick = {
                        onAction.invoke(MainScreenContract.UiAction.PlayNow)
                    },
                    icon = com.muratdayan.ui.R.drawable.ic_flash,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    iconTint = MaterialTheme.colorScheme.primary
                )
            }


        // Kaydırılabilir İçerik
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f) // Kaydırılabilir alanın esneklikle doldurulması
                .padding(Dimensions.paddingSmall),
            verticalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)
        ) {

            // Friends Section
            item {
                FastWordTextComp(
                    text = "My Friends",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = Dimensions.textSizeLarge
                )
            }
            item {
                LazyRow {
                    uiState.friends?.let {friendsList->
                        items(friendsList){friend->
                            friend.user.avatar_uri?.let {
                                FriendCardComp(
                                    friendImageUri = it,
                                    friendName = friend.user.user_name,
                                    friendImageClick = {
                                        onAction(MainScreenContract.UiAction.GoToProfile(friend.id))
                                    }
                                )
                            }
                        }
                    }

                    item {
                        FriendCardComp(
                            friendImageUri = uiState.userInfo?.avatar_uri ?: "",
                            friendName = "Invite Friends",
                            buttonTitle = "Invite",
                            buttonIconVisible = false,
                            iconTextVisible = false,
                            buttonContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                            friendImageClick = {
                            }
                        )
                    }
                }
            }

            // Invitations Section
            item {
                FastWordTextComp(
                    text = "My Invitations",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = Dimensions.textSizeLarge
                )
            }
            items(3) {
                FastWordGameCardComp(
                    name = "FastWord",
                    cardInfo = "Test",
                    imagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar)
                )
            }

            // Last Games Section
            item {
                FastWordTextComp(
                    text = "My Last Games",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = Dimensions.textSizeLarge
                )
            }
            items(13) {
                FastWordGameCardComp(
                    name = "FastWord",
                    cardInfo = "Test",
                    score = "2-0",
                    imagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar)
                )
            }
        }

        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .consumeWindowInsets(WindowInsets.navigationBars)
                .then(Modifier.padding(bottom = 0.dp)),
            containerColor = MaterialTheme.colorScheme.primary,
        ) {

            NavigationBarItem(
                selected = false,
                onClick = {
                    onAction(MainScreenContract.UiAction.GoToShop)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Home",
                        tint = MaterialTheme.colorScheme.background
                    )
                },
            )

            NavigationBarItem(
                selected = false,
                onClick = {
                    onAction(MainScreenContract.UiAction.GoToLeaderBoard)
                },
                icon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.List,
                        contentDescription = "Home",
                        tint = MaterialTheme.colorScheme.background
                    )
                },
            )

            NavigationBarItem(
                selected = false,
                onClick = {
                    onAction(MainScreenContract.UiAction.GoToFriends)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Home",
                        tint = MaterialTheme.colorScheme.background
                    )
                },
            )

            NavigationBarItem(
                selected = false,
                onClick = {
                    onAction(MainScreenContract.UiAction.GoToSettings)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Home",
                        tint = MaterialTheme.colorScheme.background
                    )
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    FastWordTheme {
        MainScreen(
            uiState = MainScreenContract.UiState(),
            uiEffect = emptyFlow(),
            onAction = {},
            navigateToShop = {},
            navigateToSettings = {},
            navigateToFriends = {},
            navigateToLeaderBoard = {},
            navigateToProfile = {},
            navigateToMatch = {},
        )
    }
}