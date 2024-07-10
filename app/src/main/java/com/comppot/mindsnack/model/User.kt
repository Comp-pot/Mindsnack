package com.comppot.mindsnack.model


data class User(
    val firstName: String,
    val lastName: String,
    val image: String,
    val preferences: UserPreferences = UserPreferences()
) {
    val fullName: String get() = "$firstName $lastName"
}

data class UserPreferences(
    val darkTheme: Boolean = false,
    val notifications: Boolean = true
)
