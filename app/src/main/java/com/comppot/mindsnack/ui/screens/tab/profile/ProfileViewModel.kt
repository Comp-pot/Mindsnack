package com.comppot.mindsnack.ui.screens.tab.profile

import androidx.lifecycle.ViewModel
import com.comppot.mindsnack.model.User
import com.comppot.mindsnack.model.toUser
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _currentUser = MutableStateFlow(User())
    val currentUser = _currentUser.asStateFlow()

    init {
        firebaseAuth.currentUser?.let {
            _currentUser.value = it.toUser()
        }
    }
}
