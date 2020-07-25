package com.rmakiyama.spatz.domain.usecase.auth

import com.rmakiyama.spatz.domain.model.auth.AuthUser
import com.rmakiyama.spatz.domain.repository.AuthRepository
import javax.inject.Inject

internal class SaveAuthUserUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : SaveAuthUserUseCase {

    override suspend fun run(command: AuthUser) {
        authRepository.save(command)
    }
}
