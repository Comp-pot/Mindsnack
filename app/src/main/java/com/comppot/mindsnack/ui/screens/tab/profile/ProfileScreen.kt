package com.comppot.mindsnack.ui.screens.tab.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.comppot.mindsnack.R
import com.comppot.mindsnack.model.User

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    val user by viewModel.currentUser.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        UserImage(imageUrl = user.image)
        SettingsCard(user = user, onEditName = { showDialog = true })
    }

    /*
    if (showDialog) {
        EditNameDialog(
            initialFirstName = user.firstName,
            initialLastName = user.lastName,
            onDismiss = { showDialog = false }
        )
    }*/
}

@Composable
private fun UserImage(imageUrl: String) {
    Box {
        AsyncImage(
            imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.outlineVariant),
            contentScale = ContentScale.Crop
        )
        /*EditIconButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .absoluteOffset(x = 15.dp, y = 15.dp)
        )*/
    }
}

@Composable
private fun EditIconButton(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Icon(
            Icons.Outlined.Edit,
            contentDescription = stringResource(id = R.string.icon_edit)
        )
    }
}

@Composable
private fun SettingsCard(user: User, onEditName: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        SettingsRow(stringResource(id = R.string.profile_settings_full_name)) {
            SettingsText(user.fullName, onClick = onEditName)
        }
        CardDivider()
        SettingsRow(stringResource(id = R.string.profile_settings_dark_theme)) {
            SettingsSwitch(user.preferences.darkTheme)
        }
        CardDivider()
        SettingsRow(stringResource(id = R.string.profile_settings_notifications)) {
            SettingsSwitch(user.preferences.notifications)
        }
    }
}

@Composable
private fun SettingsRow(title: String, content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )
        content()
    }
}

@Composable
private fun SettingsText(text: String, onClick: () -> Unit = {}) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.clickable { onClick() },
    )
}

@Composable
private fun SettingsSwitch(initialValue: Boolean) {
    var checked by remember { mutableStateOf(initialValue) }
    Switch(checked = checked, onCheckedChange = { checked = it })
}

@Composable
private fun CardDivider() {
    HorizontalDivider(
        color = MaterialTheme.colorScheme.outlineVariant,
        thickness = 1.dp
    )
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}
