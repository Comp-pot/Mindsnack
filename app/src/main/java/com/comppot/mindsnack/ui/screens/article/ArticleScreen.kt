package com.comppot.mindsnack.ui.screens.article

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

@Composable
private fun ArticleDetailsList(articleDetails: ArticleDetails) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 64.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(64.dp)
    ) {
        item {
            ArticleDetailsHeader(articleDetails)
        }
        items(articleDetails.cards) {
            TextCard(it, Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun TextCard(card: CardInfo, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = card.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = card.text, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
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
