package com.aamirchoksi.wombatreddit.ui.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aamirchoksi.wombatreddit.domain.model.RedditNewsItem
import com.aamirchoksi.wombatreddit.util.loadImg
import kotlinx.android.synthetic.main.reddit_post_item.view.*

class RedditPostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindItem(item: RedditNewsItem, callback: RedditPostCardAction) {
        itemView.apply {
            img_thumbnail.loadImg(item.thumbnail)
            description.text = item.title
            author.text = item.author
            comments.text = item.num_comments.toString() + " comments"
            setOnClickListener { callback.openRedditPost(item.url) }
        }
    }
}
