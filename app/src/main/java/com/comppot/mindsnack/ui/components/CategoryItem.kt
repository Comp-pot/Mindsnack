package com.comppot.mindsnack.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.comppot.mindsnack.model.Category

@Composable
fun CategoryItem(category: Category, isSelected: Boolean, onSelect: () -> Unit = {}) {
    val colors = if (isSelected) ButtonDefaults.buttonColors(
        disabledContainerColor = MaterialTheme.colorScheme.primary,
        disabledContentColor = MaterialTheme.colorScheme.onPrimary
    ) else ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    )
    Button(colors = colors, enabled = !isSelected, onClick = { onSelect() }) {
        Text(category.name, style = MaterialTheme.typography.labelLarge)
    }
}

@Preview
@Composable
private fun SelectedCategoryPreview() {
    val category = Category(1, "Category")
    CategoryItem(category, isSelected = true)
}

@Preview
@Composable
private fun UnselectedCategoryPreview() {
    val category = Category(1, "Category")
    CategoryItem(category, isSelected = false)
}
