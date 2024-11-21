package com.comppot.mindsnack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.comppot.mindsnack.R
import com.comppot.mindsnack.model.Article
import com.comppot.mindsnack.model.ArticleDetails
import com.comppot.mindsnack.ui.utils.DateUtils

@Composable
fun ArticleItem(
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
                PreviewTitleText(article.title, MaterialTheme.typography.titleMedium)
                DetailsText(article.postDate, article.numberOfCards, MaterialTheme.typography.bodySmall)
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
fun SavedArticleItem(
    article: Article,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
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
            PreviewTitleText(
                article.title,
                MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp)
            )
        }
    }
}

@Composable
fun ArticleDetailsHeader(articleDetails: ArticleDetails, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Text(
                articleDetails.title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            DetailsText(
                articleDetails.postDate,
                articleDetails.numberOfCards,
                MaterialTheme.typography.bodyMedium
            )
        }
        ArticleImage(
            articleDetails.image,
            modifier = Modifier
                .size(width = 150.dp, height = 190.dp)
                .clip(MaterialTheme.shapes.medium)
        )
    }
}

@Composable
private fun PreviewTitleText(title: String, style: TextStyle, modifier: Modifier = Modifier) {
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
private fun DetailsText(postDate: Long, cards: Int, style: TextStyle) {
    Text(
        stringResource(
            id = R.string.article_card_info,
            DateUtils.formatDate(postDate),
            cards
        ),
        style = style,
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
private fun ArticleItemPreview() {
    val article = Article(1, "", "How to make beautiful design using physics", 1716226826, 5, 1)
    ArticleItem(article, modifier = Modifier.fillMaxWidth())
}

@Preview
@Composable
private fun SavedArticleItemPreview() {
    val article = Article(1, "", "How to make beautiful design using physics", 1716226826, 5, 1)
    SavedArticleItem(article, modifier = Modifier.width(200.dp))
}

@Preview
@Composable
private fun ArticleDetailsHeaderPreview() {
    val article = ArticleDetails(1, "", "How to make beautiful design using physics", 1716226826, 5, 1)
    ArticleDetailsHeader(
        article,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(16.dp)
    )
}
