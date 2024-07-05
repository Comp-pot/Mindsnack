package com.comppot.mindsnack.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.comppot.mindsnack.R
import com.comppot.mindsnack.model.Article
import com.comppot.mindsnack.ui.utils.DateUtils

@Composable
fun ArticleCard(
    article: Article,
    modifier: Modifier = Modifier,
    openArticle: (Long) -> Unit = {}
) {
    Card(
        onClick = { openArticle(article.id) },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(modifier = modifier.height(IntrinsicSize.Min)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TitleText(article.title, MaterialTheme.typography.titleMedium)
                DetailsText(article.postDate, article.readTime)
            }
            ArticleImage(
                imageUrl = article.image,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(120.dp)
            )
        }
    }
}

@Composable
fun SavedArticleCard(
    article: Article,
    modifier: Modifier = Modifier,
    openArticle: (Long) -> Unit = {}
) {
    Card(
        onClick = { openArticle(article.id) },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = modifier.fillMaxWidth()) {
            ArticleImage(
                article.image,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
            )
            TitleText(
                article.title,
                MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp)
            )
        }
    }
}

@Composable
private fun TitleText(title: String, style: TextStyle, modifier: Modifier = Modifier) {
    Text(
        title,
        style = style,
        color = MaterialTheme.colorScheme.onSurface,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

@Composable
private fun DetailsText(postDate: Long, readTime: Int) {
    Text(
        stringResource(
            id = R.string.article_card_info,
            DateUtils.formatDate(postDate),
            readTime
        ),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun ArticleImage(imageUrl: String, modifier: Modifier = Modifier) {
    AsyncImage(
        imageUrl,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
private fun ArticleCardPreview() {
    val article = Article(1, "", "How to make beautiful design using physics", 1716226826, 5)
    ArticleCard(article, modifier = Modifier.fillMaxWidth())
}

@Preview
@Composable
private fun SavedArticleCardPreview() {
    val article = Article(1, "", "How to make beautiful design using physics", 1716226826, 5)
    SavedArticleCard(article, modifier = Modifier.width(200.dp))
}

