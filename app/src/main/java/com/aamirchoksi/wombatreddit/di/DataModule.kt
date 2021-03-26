package com.aamirchoksi.wombatreddit.di

import com.aamirchoksi.wombatreddit.data.remote.RedditClient
import com.aamirchoksi.wombatreddit.data.remote.RedditRepositoryImpl
import com.aamirchoksi.wombatreddit.domain.repository.RedditRepository
import com.aamirchoksi.wombatreddit.domain.usecase.GetHotRedditPostUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideRedditApi(retrofit: Retrofit): RedditClient {
        return retrofit.create(RedditClient::class.java)
    }

    @Provides
    @Singleton
    fun provideRedditRepository(repository: RedditRepositoryImpl): RedditRepository {
        return repository
    }

    @Provides
    @Singleton
    fun provideGetHotRedditUseCase(repository: RedditRepository): GetHotRedditPostUseCase {
        return GetHotRedditPostUseCase(repository)
    }
}