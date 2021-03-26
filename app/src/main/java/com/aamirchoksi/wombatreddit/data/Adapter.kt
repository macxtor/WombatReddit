package com.aamirchoksi.wombatreddit.data

import com.aamirchoksi.wombatreddit.data.dto.RedditChildrenDto
import com.aamirchoksi.wombatreddit.data.dto.RedditDataDto
import com.aamirchoksi.wombatreddit.data.dto.RedditNewsItemDto
import com.aamirchoksi.wombatreddit.data.dto.RedditPostsResponse
import com.aamirchoksi.wombatreddit.domain.model.RedditChildrenList
import com.aamirchoksi.wombatreddit.domain.model.RedditNewsItem
import com.aamirchoksi.wombatreddit.domain.model.RedditSubmissions
import com.aamirchoksi.wombatreddit.domain.model.Submissions

fun RedditPostsResponse.toEntity(): Submissions {
    return Submissions(
        data = data.toRedditSubmissionsEntity()
    )
}

fun RedditDataDto.toRedditSubmissionsEntity(): RedditSubmissions {
    return RedditSubmissions(
        children = children.map { it.toRedditChildrenListEntities() },
        after = after,
        before = before
    )
}

fun RedditChildrenDto.toRedditChildrenListEntities(): RedditChildrenList {
    return RedditChildrenList(
        data = data.toRedditNewsItemEntity()
    )
}

fun RedditNewsItemDto.toRedditNewsItemEntity(): RedditNewsItem {
    return RedditNewsItem(
        author = author,
        title = title,
        num_comments = num_comments,
        created = created,
        thumbnail = thumbnail,
        url = url
    )
}
