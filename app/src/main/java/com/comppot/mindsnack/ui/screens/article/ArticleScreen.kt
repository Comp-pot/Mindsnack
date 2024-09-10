package com.comppot.mindsnack.ui.screens.article

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.comppot.mindsnack.R
import com.comppot.mindsnack.model.ArticleDetails
import com.comppot.mindsnack.model.CardInfo
import com.comppot.mindsnack.ui.components.ArticleBottomBar
import com.comppot.mindsnack.ui.components.ArticleDetailsHeader
import com.comppot.mindsnack.ui.components.FullScreenLoading
import com.comppot.mindsnack.ui.theme.MindSnackTheme
import kotlinx.coroutines.launch

@Composable
fun ArticleScreen(
    articleId: Long,
    viewModel: ArticleViewModel = hiltViewModel(),
    navigateUp: () -> Unit = {},
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val showNotImplemented: () -> Unit = {
        coroutineScope.launch {
            val message = context.getString(R.string.snackbar_not_implemented)
            snackbarHostState.showSnackbar(message)
        }
    }

    val state = viewModel.articleState.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.fetchArticleDetails(articleId)
    }

    Scaffold(
        bottomBar = {
            ArticleBottomBar(
                isSaved = state.isSaved,
                savedCount = state.savedCount,
                onSavedClick = viewModel::updateSave,
                onShareClick = {shareLink(context, "https://comppot.com/article/$articleId")},
                navigateUp = navigateUp
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when {
                state.isLoading -> FullScreenLoading()
                state.articleDetails != null -> ArticleDetailsList(state.articleDetails)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ArticleDetailsList(articleDetails: ArticleDetails) {
    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    BoxWithConstraints(Modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(48.dp),
            state = lazyListState,
            flingBehavior = snapBehavior
        ) {
            item {
                ArticleDetailsHeader(articleDetails, modifier = Modifier.padding(vertical = 64.dp))
            }
            itemsIndexed(articleDetails.cards) { index, card ->
                Layout(content = {
                    val isFocused = isCardFocused(lazyListState, index)
                    val progressText = "${index + 1}/${articleDetails.numberOfCards}"
                    TextCard(card, isFocused, progressText, modifier = Modifier.fillMaxWidth())
                }, measurePolicy = { measurables, constraints ->
                    val placeable = measurables.first().measure(constraints)
                    val maxHeightInPx = maxHeight.roundToPx()
                    val itemHeight = placeable.height
                    val yOffset = (maxHeightInPx - itemHeight) / 2
                    val endSpace = if (index == articleDetails.numberOfCards - 1) yOffset else 0
                    val height = placeable.height + endSpace
                    layout(placeable.width, height) {
                        placeable.place(0, 0)
                    }
                })
            }
        }
    }
}

private fun isCardFocused(lazyListState: LazyListState, index: Int): Boolean {
    val currentIndex = lazyListState.firstVisibleItemIndex
    return index == currentIndex
}

@Composable
private fun TextCard(
    card: CardInfo,
    isFocused: Boolean,
    progressText: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = if (isFocused) modifier else modifier.alpha(0.5f),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (!card.title.isNullOrEmpty()) {
                Text(
                    text = card.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Text(text = card.text, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = progressText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.5f)
            )
        }
    }
}

@Preview
@Composable
private fun ArticleScreenPreview() {
    MindSnackTheme {
        ArticleScreen(1)
    }
}

fun shareLink(context: Context, link: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, link)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}
