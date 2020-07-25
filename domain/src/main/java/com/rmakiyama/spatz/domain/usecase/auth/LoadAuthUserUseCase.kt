package com.rmakiyama.spatz.domain.usecase.auth

import com.rmakiyama.spatz.domain.model.auth.AuthUser
import com.rmakiyama.spatz.domain.repository.AuthRepository
import com.rmakiyama.spatz.domain.result.Result
import com.rmakiyama.spatz.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadAuthUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : FlowUseCase<Unit, AuthUser?>() {

    override fun execute(parameters: Unit): Flow<Result<AuthUser?>> {
        return authRepository.userFlow()
    }
}
