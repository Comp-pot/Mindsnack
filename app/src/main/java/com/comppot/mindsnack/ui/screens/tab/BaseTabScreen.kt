package com.comppot.mindsnack.ui.screens.tab

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.comppot.mindsnack.ui.components.TabBottomBar
import com.comppot.mindsnack.ui.components.TabTopBar
import com.comppot.mindsnack.ui.navigation.Screen
import com.comppot.mindsnack.ui.navigation.TabNavGraph
import com.comppot.mindsnack.ui.screens.login.LoginViewModel

@Composable
fun BaseTabScreen(navController: NavHostController) {
    val context = LocalContext.current
    val loginViewModel: LoginViewModel = viewModel()
    val bottomNavController = rememberNavController()

    Scaffold(
        topBar = {
            TabTopBar(
                bottomNavController,
                navigateTo = { navController.navigate(it.route) },
                logout = {
                    loginViewModel.onSignedOut(context) {
                        navController.navigateAndPop(Screen.Login.route)
                    }
                })
        },
        bottomBar = { TabBottomBar(bottomNavController) },
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) { innerPadding ->
        TabNavGraph(
            bottomNavController,
            openArticle = { navController.navigate("${Screen.Article.route}/$it") },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(bottom = 2.dp)
        )
    }
}

private fun NavHostController.navigateAndPop(route: String) = navigate(route) {
    popUpTo(0)
}
