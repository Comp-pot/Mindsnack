package com.comppot.mindsnack.notifications.domain.model

data class Notification(
    val id: Long,
    val title: String,
    val text: String,
    val sentDate: Long,
    val wasRead: Boolean
)