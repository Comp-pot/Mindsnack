package com.comppot.mindsnack.model

data class ArticleDetails(
    val id: Long,
    val image: String,
    val title: String,
    val postDate: Long,
    val readTime: Int,
    val numberOfCards: Int = 0,
    val savedCount: Int = 0,
    val cards: List<CardInfo> = listOf()
)

data class CardInfo(
    val id: Long,
    val title: String,
    val text: String
)
