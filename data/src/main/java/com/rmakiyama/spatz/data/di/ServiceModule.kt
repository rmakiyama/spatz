package com.rmakiyama.spatz.data.di

import com.rmakiyama.spatz.data.service.SpatzAuthService
import com.rmakiyama.spatz.domain.service.AuthService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ServiceModule {

    @Binds
    internal abstract fun bindAuthService(
        authService: SpatzAuthService
    ): AuthService
}
