package com.comppot.mindsnack.ui.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.comppot.mindsnack.R
import com.comppot.mindsnack.model.Notification
import com.comppot.mindsnack.ui.components.EmptyListMessage
import com.comppot.mindsnack.ui.components.FullScreenLoading
import com.comppot.mindsnack.ui.components.TopBarBackButton
import com.comppot.mindsnack.ui.utils.DateUtils

@Composable
fun NotificationsScreen(
    navigateUp: () -> Unit = {},
    viewModel: NotificationViewModel = hiltViewModel()
) {
    val screenTitle = stringResource(id = R.string.screen_notifications)
    val state = viewModel.notificationState.collectAsState().value

    Scaffold(
        topBar = { TopBarBackButton(screenTitle, navigateUp) },
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when {
                state.isLoading -> FullScreenLoading()
                state.notifications.isEmpty() -> EmptyListMessage(stringResource(R.string.notification_screen_no_notifications))
                else -> NotificationList(state.notifications)
            }
        }
    }
}

@Composable
private fun NotificationList(notifications: List<Notification>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(notifications) {
            NotificationItem(
                it, modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.dp)
            )
        }
    }
}

@Composable
private fun NotificationItem(notification: Notification, modifier: Modifier = Modifier) {
    val backgroundColor = if (notification.wasRead) {
        MaterialTheme.colorScheme.surface
    } else {
        MaterialTheme.colorScheme.primaryContainer
    }

    Row(
        modifier = modifier
            .background(backgroundColor)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        /*
        Box(
            modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary.takeColorIf(!notification.wasRead))
        )*/
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                notification.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                notification.text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Text(
            DateUtils.getTimeDifference(notification.sentDate),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview
@Composable
private fun NotificationScreenPreview() {
    NotificationsScreen()
}
