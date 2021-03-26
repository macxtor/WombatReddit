package com.aamirchoksi.wombatreddit.domain.repository

import com.aamirchoksi.wombatreddit.domain.model.Submissions
import com.aamirchoksi.wombatreddit.util.Result

interface RedditRepository {

    suspend fun getHotPost(after: String): Result<Submissions>
}