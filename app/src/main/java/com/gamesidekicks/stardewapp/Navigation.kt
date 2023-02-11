package com.gamesidekicks.stardewapp

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.gamesidekicks.stardewapp.Screens.GIFTS_SCREEN
import com.gamesidekicks.stardewapp.Screens.GIFT_DETAIL_SCREEN
import com.gamesidekicks.stardewapp.Screens.VILLAGERS_SCREEN
import com.gamesidekicks.stardewapp.Screens.VILLAGER_DETAIL_SCREEN

private object Screens {
    const val VILLAGERS_SCREEN = "villagers"
    const val VILLAGER_DETAIL_SCREEN = "villager"
    const val GIFTS_SCREEN = "gifts"
    const val GIFT_DETAIL_SCREEN = "gift"
}

object Destinations {
    const val VILLARGERS_ROUTE = "$VILLAGERS_SCREEN?"
    const val VILLAGER_ROUTE = "$VILLAGER_DETAIL_SCREEN?"
    const val GIFTS_ROUTE = "$GIFTS_SCREEN?"
    const val GIFT_ROUTE = "$GIFT_DETAIL_SCREEN?"
}

/**
 * Models the navigation actions in the app.
 */
class NavigationActions(private val navController: NavHostController) {

    fun navigateToVillagers(){
        navController.navigate(Destinations.VILLARGERS_ROUTE) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }

    fun navigateToGifts(){
        navController.navigate(Destinations.GIFTS_ROUTE) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}