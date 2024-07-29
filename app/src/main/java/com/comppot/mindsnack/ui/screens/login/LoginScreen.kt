package com.comppot.mindsnack.ui.screens.login

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.comppot.mindsnack.ui.navigation.Screen
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult

@Composable
fun LoginScreen(navController: NavController) {
    FirebaseLoginUI(AuthManager.getSignInIntent()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            navController.navigate(Screen.Tab.route) {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
        }
    }
}

@Composable
private fun FirebaseLoginUI(
    signInIntent: Intent,
    onLoginResult: (FirebaseAuthUIAuthenticationResult) -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        contract = FirebaseAuthUIActivityResultContract(),
        onResult = onLoginResult
    )

    LaunchedEffect(true) {
        launcher.launch(signInIntent)
    }
}
