package com.aamirchoksi.wombatreddit.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RedditNewsItem(
    val author: String,
    val title: String,
    val num_comments: Int,
    val created: Long,
    val thumbnail: String,
    val url: String?
) : Parcelable