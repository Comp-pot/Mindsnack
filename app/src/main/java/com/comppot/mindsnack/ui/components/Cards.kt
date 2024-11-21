package com.comppot.mindsnack.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comppot.mindsnack.model.CardInfo

@Composable
fun CardItem(card: CardInfo, progressText: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
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

@Composable
fun SavedCardItem(card: CardInfo, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    val hasTitle = !card.title.isNullOrEmpty()
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (hasTitle) {
                Text(
                    text = card.title!!,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Text(
                text = card.text,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = if (hasTitle) 5 else 7,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun CardWithTitlePreview() {
    val card = getCardExample()
    CardItem(card, "1/20")
}

@Preview
@Composable
private fun CardOnlyTextPreview() {
    val card = getCardExample().copy(title = "")
    CardItem(card, "1/20")
}

@Preview
@Composable
private fun SavedCardWithTitlePreview() {
    val card = getCardExample()
    SavedCardItem(card, modifier = Modifier.width(200.dp))
}

@Preview
@Composable
private fun SavedCardOnlyTextPreview() {
    val card = getCardExample().copy(title = "")
    SavedCardItem(card, modifier = Modifier.width(200.dp))
}

private fun getCardExample() = CardInfo(
    1,
    "Second law",
    "The change of motion of an object is proportional to the force impressed; and is made in the direction of the straight line in which the force is impressed."
)
