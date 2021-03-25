package com.aamirchoksi.wombatreddit.domain.model

import android.os.Parcelable
import com.aamirchoksi.wombatreddit.domain.model.RedditNewsItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RedditNews(
        val after: String,
        val before: String,
        val news: List<RedditNewsItem>
) : Parcelable
