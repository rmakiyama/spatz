package com.rmakiyama.spatz.domain.di

import com.rmakiyama.spatz.domain.usecase.auth.LoadAuthUserUseCase
import com.rmakiyama.spatz.domain.usecase.auth.LoadAuthUserUseCaseImpl
import com.rmakiyama.spatz.domain.usecase.auth.SaveAuthUserUseCase
import com.rmakiyama.spatz.domain.usecase.auth.SaveAuthUserUseCaseImpl
import com.rmakiyama.spatz.domain.usecase.tweet.GetTweetsUseCase
import com.rmakiyama.spatz.domain.usecase.tweet.GetTweetsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class UseCaseModule {

    @Binds
    internal abstract fun bindGetTweetsUseCase(
        getTweetsUseCase: GetTweetsUseCaseImpl
    ): GetTweetsUseCase

    @Binds
    internal abstract fun bindLoadAuthUserUseCase(
        loadAuthUserUseCase: LoadAuthUserUseCaseImpl
    ): LoadAuthUserUseCase

    @Binds
    internal abstract fun bindSaveAuthUserUseCase(
        saveAuthUserUseCase: SaveAuthUserUseCaseImpl
    ): SaveAuthUserUseCase
}
