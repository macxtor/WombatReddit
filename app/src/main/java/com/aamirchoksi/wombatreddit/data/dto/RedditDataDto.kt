package com.aamirchoksi.wombatreddit.data.dto

data class RedditDataDto(
    val children: List<RedditChildrenDto>,
    val after: String?,
    val before: String?
)