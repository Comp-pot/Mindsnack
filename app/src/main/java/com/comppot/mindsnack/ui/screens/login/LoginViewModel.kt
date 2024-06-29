package com.comppot.mindsnack.ui.screens.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _currentUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser = _currentUser.asStateFlow()

    init {
        updateUser()
    }

    fun onSignedIn(navigateToMain: () -> Unit) {
        updateUser()
        navigateToMain()
    }

    fun onSignedOut(context: Context, navigateToAuth: () -> Unit) {
        AuthUI.getInstance()
            .signOut(context)
            .addOnCompleteListener {
                _currentUser.value = null
                navigateToAuth()
            }
    }

    private fun updateUser() {
        _currentUser.value = firebaseAuth.currentUser
    }
}
