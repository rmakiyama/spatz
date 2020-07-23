package com.rmakiyama.spatz.data.di

import com.rmakiyama.spatz.data.FakeTweetRepository
import com.rmakiyama.spatz.domain.repository.TweetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideTweetRepository(): TweetRepository {
        return FakeTweetRepository()
    }
}
