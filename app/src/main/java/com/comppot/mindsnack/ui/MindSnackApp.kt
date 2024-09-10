package com.comppot.mindsnack.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.comppot.mindsnack.ui.navigation.MainNavGraph
import com.comppot.mindsnack.ui.navigation.Screen
import com.comppot.mindsnack.ui.theme.MindSnackTheme

@Composable
fun MindSnackApp(navController: NavHostController) {
    val context = LocalContext.current


    val viewModel: AppViewModel = hiltViewModel()
    val state = viewModel.appState.collectAsState().value

    state?.let {
        MindSnackTheme(darkTheme = state.darkTheme) {
            MainNavGraph(
                navController = navController,
                startDestination = viewModel.getStartDestination(),
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
