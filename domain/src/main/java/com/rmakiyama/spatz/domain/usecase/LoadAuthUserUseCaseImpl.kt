package com.rmakiyama.spatz.domain.usecase

import com.rmakiyama.spatz.domain.model.auth.AuthUser
import com.rmakiyama.spatz.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class LoadAuthUserUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : LoadAuthUserUseCase {

    override fun load(command: Unit): Flow<AuthUser?> = authRepository.userFlow()
}
