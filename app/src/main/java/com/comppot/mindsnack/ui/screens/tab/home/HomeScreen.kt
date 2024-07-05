package com.comppot.mindsnack.ui.screens.tab.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comppot.mindsnack.model.Article
import com.comppot.mindsnack.model.Category
import com.comppot.mindsnack.ui.components.ArticleCard
import com.comppot.mindsnack.ui.components.CategoryItem

@Composable
fun HomeScreen(openArticle: (Long) -> Unit = {}) {
    val articleList = List(7) { index -> exampleArticle(id = index) }
    val categoryList = listOf(
        Category(0, "All"),
        Category(1, "Science"),
        Category(2, "Psychology"),
        Category(3, "Music"),
        Category(4, "Space"),
        Category(5, "Movies")
    )
    Column(modifier = Modifier.fillMaxSize()) {
        CategoryList(categoryList)
        ArticleList(articleList, openArticle)
    }
}

@Composable
private fun CategoryList(categoryList: List<Category>) {
    var selectedId by remember { mutableIntStateOf(0) }
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categoryList) {
            CategoryItem(it, isSelected = selectedId == it.id) { selectedId = it.id }
        }
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
private fun HomeScreenPreview() {
    HomeScreen()
}

private fun exampleArticle(id: Int) = Article(
    id.toLong(),
    "https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg",
    "How to make beautiful design using physics",
    1716226826,
    5
)
