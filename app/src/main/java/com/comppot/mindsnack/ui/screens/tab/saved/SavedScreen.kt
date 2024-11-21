package com.comppot.mindsnack.ui.screens.tab.saved

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.comppot.mindsnack.R
import com.comppot.mindsnack.model.CardInfo
import com.comppot.mindsnack.model.SavedCard

enum class SavedScreenTab(@StringRes val title: Int) {
    CARDS(R.string.saved_screen_tab_cards),
    ARTICLES(R.string.saved_screen_tab_articles)
}

@Composable
fun SavedScreen(openArticle: (Long) -> Unit = {}, viewModel: SavedViewModel = hiltViewModel()) {
    val articles = viewModel.savedArticles.collectAsState(null).value
    val cards = getCardsExample()
    var selectedTab by remember { mutableStateOf(SavedScreenTab.CARDS) }

    Column {
        SavedTabRow(selectedTab) { selectedTab = it }
        when (selectedTab) {
            SavedScreenTab.CARDS -> CardsTab(cards, openArticle)
            SavedScreenTab.ARTICLES -> ArticlesTab(articles, openArticle)
        }
    }
}

@Composable
private fun SavedTabRow(selectedTab: SavedScreenTab, onSelectTab: (SavedScreenTab) -> Unit = {}) {
    TabRow(selectedTabIndex = selectedTab.ordinal) {
        SavedScreenTab.entries.forEach {
            Tab(
                text = { Text(stringResource(it.title)) },
                selected = selectedTab == it,
                onClick = { onSelectTab(it) }
            )
        }
    }
}

private fun getCardsExample() = listOf(
    SavedCard(
        articleId = 2,
        card = CardInfo(
            id = 101,
            title = "Хом'ячий генератор",
            text = "Чи може один хом'як забезпечити енергетичні потреби дому? Спойлер = не може. Хом'як генерує дуже мало енергії, і для роботи одного світильника потрібно близько 284 хом'яків. Це підкреслює важливість ефективних джерел енергії, таких як сонячні панелі і вітряні турбіни."
        )
    ),
    SavedCard(
        articleId = 4,
        card = CardInfo(
            id = 152,
            title = null,
            text = "Розглянемо відкритий офіс, де співробітників заохочують постійно співпрацювати. Для інтровертів це може бути перевантаженням, що призведе до зниження продуктивності. Включаючи тихі зони або дозволяючи гнучкі умови дистанційної роботи, компанії можуть використовувати глибокі, обдумані внески інтровертів. Такі зміни не тільки поважають індивідуальні переваги, але й поліпшують загальну ефективність команди."
        )
    ),
    SavedCard(
        articleId = 6,
        card = CardInfo(
            id = 120,
            title = "Робоче місце",
            text = "Жінки часто змушені обирати неповний робочий день або менш оплачувані посади через обов'язки по догляду. Багато робочих структур створені навколо моделі чоловіка-годувальника, ігноруючи домогосподарства з двома доходами."
        )
    )
)

@Preview
@Composable
private fun SavedScreenPreview() {
    SavedScreen()
}
