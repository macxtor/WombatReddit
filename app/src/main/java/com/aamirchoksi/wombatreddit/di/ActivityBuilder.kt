package com.aamirchoksi.wombatreddit.di

import com.aamirchoksi.wombatreddit.ui.view.RedditPostsActivity
import com.example.reddit_wombat.di.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun bindTransactionsActivity(): RedditPostsActivity
}