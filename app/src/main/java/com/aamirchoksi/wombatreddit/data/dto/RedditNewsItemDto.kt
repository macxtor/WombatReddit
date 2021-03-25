package com.aamirchoksi.wombatreddit.data.dto

class RedditNewsItemDto(
    val author: String,
    val title: String,
    val num_comments: Int,
    val created: Long,
    val thumbnail: String,
    val url: String?
)