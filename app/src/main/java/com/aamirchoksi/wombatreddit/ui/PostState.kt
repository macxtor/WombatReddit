package com.aamirchoksi.wombatreddit.ui

import com.aamirchoksi.wombatreddit.domain.model.RedditNews

sealed class PostState {
    class Success(val redditNews: RedditNews) : PostState()
    class Error(val message: String?) : PostState()
}