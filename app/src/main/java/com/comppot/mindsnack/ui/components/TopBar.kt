@file:OptIn(ExperimentalMaterial3Api::class)

package com.comppot.mindsnack.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.comppot.mindsnack.R
import com.comppot.mindsnack.ui.navigation.Screen

@Composable
fun TabTopBar(
    bottomNavController: NavController,
    navigateTo: (Screen) -> Unit,
    logout: () -> Unit
) {
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar(
        title = {
            val titleId = when (currentRoute) {
                Screen.Tab.Home.route -> R.string.screen_home
                Screen.Tab.Search.route -> R.string.screen_search
                Screen.Tab.Saved.route -> R.string.screen_saved
                Screen.Tab.Profile.route -> R.string.screen_profile
                else -> R.string.app_name
            }
            TopBarTitle(stringResource(id = titleId))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        actions = {
            when (currentRoute) {
                Screen.Tab.Home.route -> NotificationIcon { navigateTo(Screen.Notifications) }
                Screen.Tab.Profile.route -> MenuIcon(logout)
            }
        }
    )
}

@Composable
fun TopBarBackButton(title: String, navigateUp: () -> Unit) {
    TopAppBar(
        title = { TopBarTitle(title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        navigationIcon = { BackIcon(navigateUp) },
    )
}

@Composable
private fun NotificationIcon(open: () -> Unit) {
    IconButton(onClick = open) {
        Icon(
            Icons.Outlined.Notifications,
            contentDescription = stringResource(id = R.string.screen_notifications)
        )
    }
}

@Composable
private fun MenuIcon(logout: () -> Unit) {
    OverflowMenu {
        DropdownMenuItem(
            text = { Text(stringResource(id = R.string.menu_item_logout)) },
            leadingIcon = { Icon(Icons.AutoMirrored.Default.Logout, null) },
            onClick = logout
        )
    }
}

@Composable
private fun BackIcon(navigateUp: () -> Unit) {
    IconButton(onClick = navigateUp) {
        Icon(
            Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = stringResource(id = R.string.icon_back)
        )
    }
}


@Composable
private fun OverflowMenu(content: @Composable () -> Unit) {
    var showMenu by remember { mutableStateOf(false) }

    IconButton(onClick = {
        showMenu = !showMenu
    }) {
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = stringResource(id = R.string.icon_more),
        )
    }
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { showMenu = false }
    ) {
        content()
    }
}

@Composable
private fun TopBarTitle(title: String) {
    Text(
        text = title,
        maxLines = 1
    )
}
