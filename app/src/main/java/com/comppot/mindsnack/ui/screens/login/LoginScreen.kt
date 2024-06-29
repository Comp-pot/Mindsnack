package com.comppot.mindsnack.ui.screens.login

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.comppot.mindsnack.ui.navigation.Screen
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {
    FirebaseLoginUI { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.onSignedIn {
                navController.navigate(Screen.Tab.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            }
        }
    }
}

@Composable
private fun FirebaseLoginUI(onLoginResult: (FirebaseAuthUIAuthenticationResult) -> Unit) {
    val launcher = rememberLauncherForActivityResult(
        contract = FirebaseAuthUIActivityResultContract(),
        onResult = onLoginResult
    )

    val providers = arrayListOf(
        AuthUI.IdpConfig.GoogleBuilder().build(),
        AuthUI.IdpConfig.EmailBuilder().build(),
    )

    val intent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(providers)
        .build()

    LaunchedEffect(true) {
        launcher.launch(intent)
    }
}
