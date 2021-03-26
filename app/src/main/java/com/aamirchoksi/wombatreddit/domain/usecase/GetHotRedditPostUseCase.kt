package com.aamirchoksi.wombatreddit.domain.usecase

import com.aamirchoksi.wombatreddit.domain.repository.RedditRepository

class GetHotRedditPostUseCase(private val repository: RedditRepository) {

    suspend fun execute(params: String) = repository.getHotPost(params)
}