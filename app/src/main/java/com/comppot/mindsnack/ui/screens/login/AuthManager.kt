package com.comppot.mindsnack.ui.screens.login

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

object AuthManager {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val authUI: AuthUI = AuthUI.getInstance()

    fun isAuthorized() = firebaseAuth.currentUser != null

    fun getSignInIntent(): Intent {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.EmailBuilder().build(),
        )
        return authUI.createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
    }

    fun onSignedOut(context: Context, onComplete: () -> Unit) {
        authUI.signOut(context)
            .addOnCompleteListener {
                onComplete()
            }
    }
}
