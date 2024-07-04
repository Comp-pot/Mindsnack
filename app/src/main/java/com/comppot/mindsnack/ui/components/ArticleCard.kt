package com.comppot.mindsnack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.comppot.mindsnack.R
import com.comppot.mindsnack.model.Article
import com.comppot.mindsnack.ui.utils.DateUtils

@Composable
fun ArticleCard(article: Article, modifier: Modifier = Modifier, openArticle: (Long) -> Unit = {}) {
    Card(onClick = { openArticle(article.id) }) {
        Row(
            modifier = modifier
                .background(
                    MaterialTheme.colorScheme.surface,
                    RoundedCornerShape(10.dp)
                )
                .height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Text(
                    article.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    stringResource(
                        id = R.string.article_card_info,
                        DateUtils.formatDate(article.postDate),
                        article.readTime
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            AsyncImage(
                article.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(120.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview
@Composable
private fun ArticleCardPreview() {
    val article = Article(1, "", "How to make beautiful design using physics", 1716226826, 5)
    ArticleCard(article, modifier = Modifier.fillMaxWidth())
}
