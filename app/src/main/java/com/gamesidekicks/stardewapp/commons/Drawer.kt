package com.gamesidekicks.stardewapp.commons

import androidx.compose.material3.*
import com.gamesidekicks.stardewapp.ui.theme.StardewAppTheme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gamesidekicks.stardewapp.Destinations
import com.gamesidekicks.stardewapp.NavigationActions
import com.gamesidekicks.stardewapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AppModalDrawer(
    drawerState: DrawerState,
    currentRoute: String,
    navigationActions: NavigationActions,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                currentRoute = currentRoute,
                navigateToVillagers = { navigationActions.navigateToVillagers() },
                navigateToGifts = { navigationActions.navigateToGifts() },
                closeDrawer = { coroutineScope.launch { drawerState.close() } }
            )
        }
    ) {
        content()
    }
}

@Composable
private fun AppDrawer(
    currentRoute: String,
    navigateToVillagers: () -> Unit,
    navigateToGifts: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            DrawerHeader()
            DrawerButton(
                painter = painterResource(id = R.drawable.baseline_people_24),
                label = stringResource(id = R.string.villagers),
                isSelected = currentRoute == Destinations.VILLARGERS_ROUTE,
                action = {
                    navigateToVillagers()
                    closeDrawer()
                }
            )
            DrawerButton(
                painter = painterResource(id = R.drawable.baseline_card_giftcard_24),
                label = stringResource(id = R.string.gifts),
                isSelected = currentRoute == Destinations.GIFTS_ROUTE,
                action = {
                    navigateToGifts()
                    closeDrawer()
                }
            )
        }
    }
}

@Composable
private fun DrawerHeader(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(dimensionResource(id = R.dimen.header_height))
    ) {
        Image(
            painter = painterResource(
                if(isSystemInDarkTheme()) R.drawable.im_background_night
                else R.drawable.im_background_day
            ),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize(1f)
        )
        Text(
            text = stringResource(id = R.string.app_name)
        )
    }
}

@Composable
private fun DrawerButton(
    painter: Painter,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tintColor = if (isSelected) {
        MaterialTheme.colorScheme.secondary
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
    }

    TextButton(
        onClick = action,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.horizontal_margin))
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painter,
                contentDescription = null, // decorative
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = tintColor
            )
        }
    }
}

@Preview("Drawer contents")
@Composable
fun PreviewAppDrawer() {
    StardewAppTheme() {
        Surface {
            AppDrawer(
                currentRoute = Destinations.GIFTS_ROUTE,
                navigateToVillagers = {},
                navigateToGifts = {},
                closeDrawer = {}
            )
        }
    }
}
