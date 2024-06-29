package com.comppot.mindsnack.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.comppot.mindsnack.ui.screens.login.LoginViewModel
import com.comppot.mindsnack.ui.navigation.MainNavGraph
import com.comppot.mindsnack.ui.navigation.Screen
import com.comppot.mindsnack.ui.theme.MindSnackTheme

@Composable
fun MindSnackApp() {
    MindSnackTheme {
        val navController = rememberNavController()
        val loginViewModel: LoginViewModel = viewModel()

        val user by loginViewModel.currentUser.collectAsState()
        val startDestination = if (user == null) Screen.Login else Screen.Tab
        MainNavGraph(navController, startDestination)
    }
}
