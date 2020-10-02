package com.rmakiyama.spatz.data.di

import com.rmakiyama.spatz.data.RequestTokenSource
import com.rmakiyama.spatz.data.TwitterAuthTokenSource
import com.rmakiyama.spatz.data.TwitterSessionSource
import com.rmakiyama.spatz.data.UserDataSource
import com.rmakiyama.spatz.data.local.RequestTokenInMemoryDataSource
import com.rmakiyama.spatz.data.local.TwitterAuthTokenInMemoryDataSource
import com.rmakiyama.spatz.data.local.UserInMemoryDataSource
import com.rmakiyama.spatz.data.preference.TwitterSessionPreferencesStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class PersistenceModule {

    @Binds
    @Named("local")
    @Singleton
    internal abstract fun bindLocalUserDataSource(
        inMemoryDataSource: UserInMemoryDataSource
    ): UserDataSource

    @Binds
    @Singleton
    internal abstract fun bindLocalTwitterSessionDataSource(
        localDataSource: TwitterSessionPreferencesStorage
    ): TwitterSessionSource

    @Binds
    @Singleton
    internal abstract fun bindLocalRequestTokenDataSource(
        dataSource: RequestTokenInMemoryDataSource
    ): RequestTokenSource

    @Binds
    @Singleton
    internal abstract fun bindTwitterAuthTokenDataSource(
        localDataSource: TwitterAuthTokenInMemoryDataSource
    ): TwitterAuthTokenSource
}
