package com.aamirchoksi.wombatreddit.data.remote

import com.aamirchoksi.wombatreddit.data.toEntity
import com.aamirchoksi.wombatreddit.domain.model.Submissions
import com.aamirchoksi.wombatreddit.domain.repository.RedditRepository
import com.aamirchoksi.wombatreddit.util.Result
import javax.inject.Inject

class RedditRepositoryImpl @Inject constructor(
    private val client: RedditClient
) : RedditRepository {

    companion object {
        const val POST_LIMIT = "10"
    }

    override suspend fun getHotPost(after: String): Result<Submissions> {
        return try {
            Result.Success(client.getHotPosts(after, POST_LIMIT).toEntity())
        } catch (exception: Exception) {
            return Result.Failure(exception)
        }
    }
}