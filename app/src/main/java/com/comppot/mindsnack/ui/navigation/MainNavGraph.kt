package com.comppot.mindsnack.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.comppot.mindsnack.ui.screens.article.ArticleScreen
import com.comppot.mindsnack.ui.screens.login.LoginScreen
import com.comppot.mindsnack.ui.screens.notifications.NotificationsScreen
import com.comppot.mindsnack.ui.screens.tab.BaseTabScreen

const val ARTICLE_ID = "articleId"

@Composable
fun MainNavGraph(navController: NavHostController, startDestination: Screen) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        composable(route = Screen.Login.route) { LoginScreen(navController) }
        composable(route = Screen.Tab.route) { BaseTabScreen(navController) }
        composable(
            route = Screen.Article.route + "/{$ARTICLE_ID}",
            arguments = listOf(navArgument(ARTICLE_ID) { type = NavType.LongType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val articleId = arguments.getLong(ARTICLE_ID)
            ArticleScreen(articleId) { navController.navigateUp() }
        }
        composable(route = Screen.Notifications.route) { NotificationsScreen { navController.navigateUp() } }
    }
}
