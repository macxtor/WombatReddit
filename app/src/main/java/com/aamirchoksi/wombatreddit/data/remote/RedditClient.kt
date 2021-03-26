package com.aamirchoksi.wombatreddit.data.remote

import com.aamirchoksi.wombatreddit.data.dto.RedditPostsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditClient {

    @GET(REDDIT_HOT_ENDPOINT)
    suspend fun getHotSubmissions(
        @Query("after") after: String,
        @Query("limit") limit: String
    ): RedditPostsResponse

    @GET(REDDIT_HOT_ENDPOINT)
    suspend fun getHotPosts(
        @Query("after") after: String,
        @Query("limit") limit: String
    ): RedditPostsResponse
}