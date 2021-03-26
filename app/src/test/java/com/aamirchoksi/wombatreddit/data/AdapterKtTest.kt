package com.aamirchoksi.wombatreddit.data

import com.aamirchoksi.wombatreddit.data.dto.RedditChildrenDto
import com.aamirchoksi.wombatreddit.data.dto.RedditNewsItemDto
import com.aamirchoksi.wombatreddit.data.dto.RedditPostsResponse
import com.aamirchoksi.wombatreddit.domain.model.RedditChildrenList
import com.aamirchoksi.wombatreddit.domain.model.RedditNewsItem
import com.aamirchoksi.wombatreddit.domain.model.RedditSubmissions
import com.aamirchoksi.wombatreddit.domain.model.Submissions
import org.assertj.core.api.Java6Assertions.assertThat

import org.junit.Test

class RedditPostsNetworkToDomainMapperTest {

    @Test
    fun `should convert reddit news item to domain reddit news item`() {
        val expectedValue = getRedditNewsItem()
        val networkObject = getRedditNewsItemDto()

        val result = networkObject.toRedditNewsItemEntity()

        assertThat(result).isEqualToComparingFieldByField(expectedValue)
    }

    @Test
    fun `should convert reddit submission dto to domain reddit submission entity`() {
        val expectedValue = getRedditChildrenList()
        val networkObject = getRedditChildrenListDto()

        val result = networkObject.toRedditChildrenListEntities()

        assertThat(result).isEqualToComparingFieldByField(expectedValue)
    }

    @Test
    fun `should convert reddit children list to domain reddit childrent list item`() {
        val expectedValue = getRedditChildrenList()
        val networkObject = getRedditChildrenListDto()

        val result = networkObject.toRedditChildrenListEntities()

        assertThat(result).isEqualToComparingFieldByField(expectedValue)
    }


    fun getRedditNewsItemDto() = RedditNewsItemDto(
        author = "author",
        title = "title",
        num_comments = 4,
        created = 1L,
        thumbnail = "thumbnai",
        url = "url"
    )

    fun getRedditNewsItem() = RedditNewsItem(
        author = "author",
        title = "title",
        num_comments = 4,
        created = 1L,
        thumbnail = "thumbnai",
        url = "url"
    )

    fun getRedditChildrenList() = RedditChildrenList(
        data = getRedditNewsItem()
    )

    fun getRedditChildrenListDto() = RedditChildrenDto(
        data = getRedditNewsItemDto()
    )

    fun getRedditSubmissions() = RedditSubmissions(
        children = listOf(getRedditChildrenList()),
        after = "after",
        before = "before"
    )
}