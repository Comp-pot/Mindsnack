package com.comppot.mindsnack.ui.screens.article

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comppot.mindsnack.model.ArticleDetails
import com.comppot.mindsnack.model.CardInfo
import com.comppot.mindsnack.ui.components.ArticleBottomBar
import com.comppot.mindsnack.ui.components.ArticleDetailsHeader
import com.comppot.mindsnack.ui.theme.MindSnackTheme

@Composable
fun ArticleScreen(articleId: Long, navigateUp: () -> Unit = {}) {
    val articleDetails = exampleArticleDetails(articleId)

    Scaffold(
        bottomBar = { ArticleBottomBar(articleDetails.savedCount, navigateUp) },
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            ArticleDetailsList(articleDetails)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ArticleDetailsList(articleDetails: ArticleDetails) {
    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    LazyColumn(
        contentPadding = PaddingValues(vertical = 64.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        state = lazyListState,
        flingBehavior = snapBehavior
    ) {
        item {
            ArticleDetailsHeader(articleDetails, modifier = Modifier.padding(bottom = 50.dp))
        }
        itemsIndexed(articleDetails.cards) { index, card ->
            val isFocused = isCardFocused(lazyListState, index)
            val progressText = "${index + 1}/${articleDetails.numberOfCards}"
            TextCard(card, isFocused, progressText, modifier = Modifier.fillMaxWidth())
        }
        item {
            Spacer(modifier = Modifier.height(100.dp))
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
            Text(text = card.title, style = MaterialTheme.typography.titleLarge)
            Text(text = card.text, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = progressText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth().alpha(0.5f)
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

private fun exampleArticleDetails(id: Long) = ArticleDetails(
    id,
    "https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg",
    "How to make beautiful design using physics",
    1716226826,
    5,
    7,
    0,
    List(7) { index ->
        CardInfo(
            index.toLong(),
            "Newton's second law",
            "The acceleration that a body acquires due to the action of a force is directly proportional to this force and inversely proportional to the mass of the body.\n\nThe object referred to in Newton's second law is a material point that has an inherent property of inertia, the magnitude of which is characterized by mass."
        )
    }
)
