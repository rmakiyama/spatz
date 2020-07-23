package com.rmakiyama.spatz.di

import com.rmakiyama.spatz.domain.usecase.GetTweetsUseCase
import com.rmakiyama.spatz.domain.usecase.GetTweetsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetTweetsUseCase(
        analyticsServiceImpl: GetTweetsUseCaseImpl
    ): GetTweetsUseCase
}
