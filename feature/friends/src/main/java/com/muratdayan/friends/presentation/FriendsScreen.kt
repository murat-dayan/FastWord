package com.muratdayan.friends.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.friends.R
import com.muratdayan.friends.util.FriendsListType
import com.muratdayan.ui.components.FastWordAdvertHeaderComp
import com.muratdayan.ui.components.FastWordBarHeaderComp
import com.muratdayan.ui.components.FastWordBaseCardComp
import com.muratdayan.ui.components.FastWordButtonComp
import com.muratdayan.ui.components.FastWordGameCardComp
import com.muratdayan.ui.components.FastWordProfileImageComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme
import com.muratdayan.ui.theme.extendedColors

@Composable
fun FriendsScreenRoot(
    modifier: Modifier = Modifier,
) {
    FriendsScreen(
        modifier = modifier
    )
}

@Composable
private fun FriendsScreen(
    modifier: Modifier = Modifier,
) {

    var selectedListTitleIndex by remember { mutableStateOf(FriendsListType.MY_FRIENDS) }

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
                    currentEnergy = 1,
                    maxEnergy = 10,
                    coinValue = 0,
                    emeraldValue = 0
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
            textAlignment = TextAlign.Center
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
            FastWordBaseCardComp(
                modifier = Modifier
                    .weight(1f),
                height = 50,
                onClick = {
                    selectedListTitleIndex = FriendsListType.REQUESTS
                },
                containerColor = if (selectedListTitleIndex == FriendsListType.REQUESTS)
                    MaterialTheme.colorScheme.background
                else MaterialTheme.extendedColors.customBlue.colorContainer.copy(0.8f)
            ) {
                FastWordTextComp(
                    text = "Everyone",
                    fontWeight = FontWeight.Bold,
                    color = if (selectedListTitleIndex == FriendsListType.REQUESTS)
                        MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.background
                )
            }
        }
        LazyColumn {
            items(5) {
                FastWordGameCardComp(
                    name = "Murat",
                    imagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FriendsScreenPreview() {
    FastWordTheme {
        FriendsScreen()
    }
}