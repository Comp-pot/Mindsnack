package com.comppot.mindsnack.ui.screens.tab.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comppot.mindsnack.model.Article
import com.comppot.mindsnack.ui.components.SavedArticleCard

@Composable
fun SavedScreen(openArticle: (Long) -> Unit = {}) {
    val articleList = List(7) { index -> exampleArticle(id = index) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(articleList) {
            SavedArticleCard(it, Modifier.fillMaxWidth(), openArticle)
        }
    }
}

@Preview
@Composable
private fun SavedScreenPreview() {
    SavedScreen()
}

private fun exampleArticle(id: Int) = Article(
    id.toLong(),
    "https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg",
    "How to make beautiful design using physics",
    1716226826,
    5
)
