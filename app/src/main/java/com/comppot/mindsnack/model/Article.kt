package com.comppot.mindsnack.model

data class Article(
    val id: Long,
    val image: String,
    val title: String,
    val postDate: Long,
    val readTime: Int
)