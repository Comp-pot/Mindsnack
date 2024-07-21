package com.comppot.mindsnack.ui.screens.tab.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comppot.mindsnack.model.Article
import com.comppot.mindsnack.ui.components.ArticleCard
import com.comppot.mindsnack.ui.components.ArticleSearchBar

@Composable
fun SearchScreen(openArticle: (Long) -> Unit = {}) {
    val articleList = List(7) { index -> exampleArticle(id = index) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ArticleSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        ArticleList(articleList = articleList, openArticle)
    }
}

@Composable
private fun ArticleList(articleList: List<Article>, openArticle: (Long) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(articleList) {
            ArticleCard(it, Modifier.fillMaxWidth(), openArticle)
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}

private fun exampleArticle(id: Int) = Article(
    id.toLong(),
    "https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg",
    "How to make beautiful design using physics",
    1716226826,
    5
)
