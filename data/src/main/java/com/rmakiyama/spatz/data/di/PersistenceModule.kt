package com.rmakiyama.spatz.data.di

import com.rmakiyama.spatz.data.UserDataSource
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
}
