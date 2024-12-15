package com.muratdayan.leaderboard.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.muratdayan.leaderboard.R
import com.muratdayan.leaderboard.presentation.components.LeaderBoardCardComp
import com.muratdayan.leaderboard.util.ListType
import com.muratdayan.ui.components.FastWordBaseCardComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun LeaderBoardScreenRoot(
    modifier: Modifier = Modifier
) {
    LeaderBoardScreen(
        modifier = modifier
    )
}

@Composable
private fun LeaderBoardScreen(
    modifier: Modifier = Modifier
) {


    var selectedListTitleIndex by remember { mutableStateOf(ListType.MY_FRIENDS) }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(Dimensions.paddingSmall),
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
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

            FastWordTextComp(
                text = "Top List",
                modifier = Modifier,
                fontSize = Dimensions.textSizeLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(1.dp))

        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            FastWordBaseCardComp(
                modifier = Modifier
                    .weight(1f),
                onClick = {
                    selectedListTitleIndex = ListType.MY_FRIENDS
                },
                containerColor = if (selectedListTitleIndex == ListType.MY_FRIENDS)
                    MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f)
                else MaterialTheme.colorScheme.outline
            ) {
                FastWordTextComp(
                    text = "My Friends",
                    fontWeight = FontWeight.Bold
                )
            }
            FastWordBaseCardComp(
                modifier = Modifier
                    .weight(1f),
                onClick = {
                    selectedListTitleIndex = ListType.EVERYONE
                },
                containerColor = if (selectedListTitleIndex == ListType.EVERYONE)
                    MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f)
                else MaterialTheme.colorScheme.outline
            ) {
                FastWordTextComp(
                    text = "Everyone",
                    fontWeight = FontWeight.Bold
                )
            }
        }

        LazyColumn {
            items(20) { index ->
                LeaderBoardCardComp(
                    userName = "Murat",
                    scoreText = "100",
                    orderText = "${index + 1}",
                    iconOrderPainter = when (index) {
                        0 -> painterResource(com.muratdayan.ui.R.drawable.ic_crown)
                        1 -> painterResource(com.muratdayan.ui.R.drawable.ic_crown)
                        2 -> painterResource(com.muratdayan.ui.R.drawable.ic_crown)
                        else -> null
                    },
                    iconTint = when (index) {
                        0 -> MaterialTheme.colorScheme.secondaryContainer
                        1 -> MaterialTheme.colorScheme.background
                        2 -> MaterialTheme.colorScheme.secondaryContainer.copy(0.7f)
                        else -> null
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LeaderBoardScreenPreview() {
    FastWordTheme {
        LeaderBoardScreen()
    }
}