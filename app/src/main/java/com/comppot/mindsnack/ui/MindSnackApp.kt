package com.comppot.mindsnack.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.comppot.mindsnack.ui.navigation.MainNavGraph
import com.comppot.mindsnack.ui.navigation.Screen
import com.comppot.mindsnack.ui.screens.login.AuthManager
import com.comppot.mindsnack.ui.theme.MindSnackTheme

@Composable
fun MindSnackApp() {
    MindSnackTheme {
        val navController = rememberNavController()

        val isAuthorized = AuthManager.isAuthorized()
        val startDestination = if (!isAuthorized) Screen.Login else Screen.Tab
        MainNavGraph(navController, startDestination)
    }
}
