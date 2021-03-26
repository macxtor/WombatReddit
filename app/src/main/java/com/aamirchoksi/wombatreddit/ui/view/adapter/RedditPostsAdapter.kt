package com.aamirchoksi.wombatreddit.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aamirchoksi.wombatreddit.R
import com.aamirchoksi.wombatreddit.domain.model.RedditNewsItem

class RedditPostsAdapter(private val callback: RedditPostCardAction) :
    RecyclerView.Adapter<RedditPostViewHolder>() {

    private var postItems: MutableList<RedditNewsItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditPostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.reddit_post_item, parent, false)
        return RedditPostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RedditPostViewHolder, position: Int) {
        holder.bindItem(postItems[position], callback)
    }

    override fun getItemCount() = postItems.size


    fun addNews(news: List<RedditNewsItem>) {
        postItems.addAll(news)
        notifyDataSetChanged()
    }

    fun clearAndAddNews(news: List<RedditNewsItem>) {
        postItems.clear()
        notifyItemRangeRemoved(0, getLastPosition())
        postItems.addAll(news)
        notifyItemRangeInserted(0, postItems.size)
    }

    fun getNews(): List<RedditNewsItem> = postItems

    private fun getLastPosition() = if (postItems.lastIndex == -1) 0 else postItems.lastIndex

}