package com.rmakiyama.spatz.data.di

import com.rmakiyama.spatz.data.TwitterSessionSource
import com.rmakiyama.spatz.data.DataTwitterSessionRepository
import com.rmakiyama.spatz.data.DataUserRepository
import com.rmakiyama.spatz.data.FakeTweetRepository
import com.rmakiyama.spatz.data.UserDataSource
import com.rmakiyama.spatz.domain.repository.TwitterSessionRepository
import com.rmakiyama.spatz.domain.repository.TweetRepository
import com.rmakiyama.spatz.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Named

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideTweetRepository(): TweetRepository {
        return FakeTweetRepository()
    }

    @Provides
    @ActivityRetainedScoped
    fun provideUserRepository(
        @Named("local") localDataSource: UserDataSource
    ): UserRepository {
        return DataUserRepository(localDataSource)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideAuthRepository(
        localDataSource: TwitterSessionSource
    ): TwitterSessionRepository {
        return DataTwitterSessionRepository(localDataSource)
    }
}
