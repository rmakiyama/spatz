package com.rmakiyama.spatz.domain.usecase.auth

import com.rmakiyama.spatz.domain.model.auth.AuthUser
import com.rmakiyama.spatz.domain.repository.AuthRepository
import com.rmakiyama.spatz.domain.usecase.SuspendUseCase
import javax.inject.Inject

class SaveAuthUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : SuspendUseCase<AuthUser, Unit>() {

    override suspend fun execute(command: AuthUser) {
        authRepository.save(command)
    }
}
