@file:OptIn(ExperimentalMaterial3Api::class)

package com.comppot.mindsnack.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.comppot.mindsnack.R

@Composable
fun ArticleSearchBar() {
    val focusManager = LocalFocusManager.current
    var text by rememberSaveable { mutableStateOf("") }

    DockedSearchBar(
        query = text,
        onQueryChange = { text = it },
        onSearch = { focusManager.clearFocus() },
        active = false,
        onActiveChange = { },
        placeholder = { Text(stringResource(id = R.string.search_bar_placeholder)) },
        leadingIcon = { SearchIcon() },
        trailingIcon = {
            if (text.isNotEmpty()) {
                ClearIcon { text = "" }
            }
        },
        colors = SearchBarDefaults.colors(containerColor = MaterialTheme.colorScheme.surface),
        tonalElevation = 0.dp,
    ) {}
}

@Composable
private fun SearchIcon() {
    Icon(
        Icons.Default.Search,
        contentDescription = stringResource(id = R.string.icon_search)
    )
}

@Composable
private fun ClearIcon(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            Icons.Outlined.Close,
            contentDescription = stringResource(id = R.string.icon_clear)
        )
    }
}
