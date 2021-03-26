package com.aamirchoksi.wombatreddit.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aamirchoksi.wombatreddit.domain.model.RedditNews
import com.aamirchoksi.wombatreddit.domain.model.Submissions
import com.aamirchoksi.wombatreddit.domain.usecase.GetHotRedditPostUseCase
import com.aamirchoksi.wombatreddit.util.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

public class RedditPostViewModel @Inject constructor(
    private val getHotRedditPostUseCase: GetHotRedditPostUseCase
) : ViewModel() {

    val newsState: MutableLiveData<PostState> = MutableLiveData()

    fun fetchNews(after: String) = GlobalScope.launch {
        try {
            when (val result = getHotRedditPostUseCase.execute(after)) {
                is Result.Success -> handleSuccess(result.data)
                is Result.Failure -> handleFailure(result.error)
            }
        } catch (e: Throwable) {
            newsState.postValue(PostState.Error(e.message))
        }
    }

    private fun handleFailure(error: Throwable) {
        newsState.postValue(PostState.Error(error.message))
    }

    private fun handleSuccess(data: Submissions) {
        val news = process(data)
        newsState.postValue(PostState.Success(news))
    }

    private fun process(response: Submissions) = RedditNews(
        response.data.after.orEmpty(),
        response.data.before.orEmpty(),
        response.data.children.map { it.data }
    )
}