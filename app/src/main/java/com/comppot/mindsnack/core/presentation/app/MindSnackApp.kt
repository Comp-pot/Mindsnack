package com.comppot.mindsnack.core.presentation.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.comppot.mindsnack.core.presentation.navigation.MainNavGraph
import com.comppot.mindsnack.core.presentation.Screen
import com.comppot.mindsnack.ui.theme.MindSnackTheme

@Composable
fun MindSnackApp() {
    val context = LocalContext.current
    val navController = rememberNavController()

    val viewModel: AppViewModel = hiltViewModel()
    val state = viewModel.appState.collectAsState().value

    state?.let {
        MindSnackTheme(darkTheme = state.themeMode.isDarkMode()) {
            MainNavGraph(
                navController = navController,
                startDestination = viewModel.getStartDestination(),
                unreadNotifications = state.unreadNotifications,
                onLogout = {
                    viewModel.logout(context) {
                        navController.navigateAndPop(Screen.Login.route)
                    }
                }
            )
        }
    }
}

private fun NavHostController.navigateAndPop(route: String) = navigate(route) {
    popUpTo(0)
}
