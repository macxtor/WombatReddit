package com.aamirchoksi.wombatreddit.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RedditSubmissions(
    val children: List<RedditChildrenList>,
    val after: String?,
    val before: String?
):Parcelable