package com.comppot.mindsnack.model

import com.google.firebase.auth.FirebaseUser


data class User(
    val fullName: String = "",
    val image: String = "",
    val preferences: UserPreferences = UserPreferences()
)

data class UserPreferences(
    val darkTheme: Boolean = false,
    val notifications: Boolean = true
)

fun FirebaseUser.toUser() = User(
    fullName = displayName.orEmpty(),
    image = photoUrl.toString()
)
