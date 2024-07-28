package com.comppot.mindsnack.ui.screens.login

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun isAuthorized() = firebaseAuth.currentUser != null

    fun getSignInIntent(): Intent {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.EmailBuilder().build(),
        )
        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
    }

    fun onSignedOut(context: Context, onComplete: () -> Unit) {
        AuthUI.getInstance()
            .signOut(context)
            .addOnCompleteListener {
                onComplete()
            }
    }
}
