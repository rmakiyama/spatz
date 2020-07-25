package com.rmakiyama.spatz.data.di

import com.rmakiyama.spatz.data.AuthDataSource
import com.rmakiyama.spatz.data.UserDataSource
import com.rmakiyama.spatz.data.local.AuthInMemoryDataSource
import com.rmakiyama.spatz.data.local.UserInMemoryDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Named

@Module
@InstallIn(ApplicationComponent::class)
abstract class PersistenceModule {

    @Binds
    @Named("local")
    internal abstract fun bindLocalUserDataSource(
        inMemoryDataSource: UserInMemoryDataSource
    ): UserDataSource

    @Binds
    internal abstract fun bindLocalAuthDataSource(
        // FIXME
        localDataSource: AuthInMemoryDataSource
    ): AuthDataSource
}
