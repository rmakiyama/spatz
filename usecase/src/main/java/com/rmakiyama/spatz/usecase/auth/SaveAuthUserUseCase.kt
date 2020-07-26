package com.rmakiyama.spatz.usecase.auth

import com.rmakiyama.spatz.domain.model.auth.AuthUser
import com.rmakiyama.spatz.domain.repository.AuthRepository
import javax.inject.Inject

class SaveAuthUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : com.rmakiyama.spatz.usecase.SuspendUseCase<AuthUser, Unit>() {

    override suspend fun execute(command: AuthUser) {
        authRepository.save(command)
    }
}
