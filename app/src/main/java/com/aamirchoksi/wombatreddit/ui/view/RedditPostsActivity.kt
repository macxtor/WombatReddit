package com.aamirchoksi.wombatreddit.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aamirchoksi.wombatreddit.R
import com.aamirchoksi.wombatreddit.domain.model.RedditNews
import com.aamirchoksi.wombatreddit.domain.usecase.GetHotRedditPostUseCase
import com.aamirchoksi.wombatreddit.ui.view.adapter.RedditPostsAdapter
import com.aamirchoksi.wombatreddit.ui.view.adapter.RedditPostCardAction
import com.aamirchoksi.wombatreddit.ui.viewModel.PostState
import com.aamirchoksi.wombatreddit.ui.viewModel.RedditPostViewModel
import com.aamirchoksi.wombatreddit.util.InfiniteScrollListener
import com.aamirchoksi.wombatreddit.util.androidLazy
import com.aamirchoksi.wombatreddit.util.createFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_reddit_post.*
import javax.inject.Inject

class RedditPostsActivity : AppCompatActivity(), RedditPostCardAction {

    companion object {
        private const val KEY_REDDIT_NEWS = "redditNews"
    }

    @Inject
    lateinit var getHotRedditPostUseCase: GetHotRedditPostUseCase

    private var redditNews: RedditNews? = null
    private val newsAdapter by androidLazy { RedditPostsAdapter(this) }
    private lateinit var redditPostViewModel: RedditPostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reddit_post)
        observeViewModel()
        setRecyclerView()
        fetchRedditPosts(savedInstanceState)
    }

    private fun observeViewModel() {
        val factory = RedditPostViewModel(getHotRedditPostUseCase).createFactory()
        redditPostViewModel =
            ViewModelProviders.of(this, factory).get(RedditPostViewModel::class.java)

        swiperefresh.setOnRefreshListener {
            requestNews()
        }
        redditPostViewModel.newsState.observe(this, {
            manageState(it)
        })
    }

    private fun setRecyclerView() {
        post_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestNews() }, linearLayout))
        }

        post_list.adapter = newsAdapter
    }


    private fun fetchRedditPosts(savedInstanceState: Bundle?) {
        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_REDDIT_NEWS)) {
            redditNews = savedInstanceState.get(KEY_REDDIT_NEWS) as RedditNews
            newsAdapter.clearAndAddNews(redditNews!!.news)
        } else {
            requestNews()
        }
    }

    private fun requestNews() {
        progress_bar.visibility = VISIBLE
        redditPostViewModel.fetchNews(redditNews?.after.orEmpty())
    }

    private fun manageState(postState: PostState?) {
        val state = postState ?: return
        when (state) {
            is PostState.Success -> {
                swiperefresh.isRefreshing = false
                progress_bar.visibility = GONE
                redditNews = state.redditNews
                newsAdapter.addNews(state.redditNews.news)
            }
            is PostState.Error -> {
                progress_bar.visibility = GONE
                swiperefresh.isRefreshing = false
                Snackbar.make(post_list, state.message.orEmpty(), Snackbar.LENGTH_INDEFINITE)
                    .setAction("RETRY") { requestNews() }
                    .show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = newsAdapter.getNews()
        if (redditNews != null && news.isNotEmpty()) {
            outState.putParcelable(KEY_REDDIT_NEWS, redditNews?.copy(news = news))
        }
    }

    override fun openRedditPost(url: String?) {
        if (url.isNullOrEmpty()) {
            Snackbar.make(post_list, "No URL assigned to this news", Snackbar.LENGTH_LONG).show()
        } else {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}