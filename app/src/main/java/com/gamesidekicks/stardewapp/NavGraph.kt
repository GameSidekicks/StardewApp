package com.gamesidekicks.stardewapp

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gamesidekicks.stardewapp.commons.AppModalDrawer
import com.gamesidekicks.stardewapp.gifts.GiftsScreen
import com.gamesidekicks.stardewapp.villagers.VillagersScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    startDestination: String = Destinations.VILLARGERS_ROUTE,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    }
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            Destinations.VILLARGERS_ROUTE
        ) {
            AppModalDrawer(drawerState, currentRoute, navActions) {
                VillagersScreen(openDrawer = { coroutineScope.launch { drawerState.open() } })
            }
        }
        composable(
            Destinations.GIFTS_ROUTE
        ) {
            AppModalDrawer(drawerState, currentRoute, navActions) {
                GiftsScreen(openDrawer = { coroutineScope.launch { drawerState.open() } })
            }
        }
    }
}
