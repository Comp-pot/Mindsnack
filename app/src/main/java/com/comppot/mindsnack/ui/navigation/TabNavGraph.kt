package com.comppot.mindsnack.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.comppot.mindsnack.ui.screens.tab.home.HomeScreen
import com.comppot.mindsnack.ui.screens.tab.profile.ProfileScreen
import com.comppot.mindsnack.ui.screens.tab.saved.SavedScreen
import com.comppot.mindsnack.ui.screens.tab.search.SearchScreen

@Composable
fun TabNavGraph(
    navController: NavHostController,
    openArticle: (Long) -> Unit,
    logout: () -> Unit,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Tab.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Tab.Home.route) { HomeScreen(openArticle) }
        composable(Screen.Tab.Search.route) { SearchScreen() }
        composable(Screen.Tab.Saved.route) { SavedScreen(openArticle) }
        composable(Screen.Tab.Profile.route) { ProfileScreen(logout) }
    }
}
