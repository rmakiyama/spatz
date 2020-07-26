package com.rmakiyama.spatz.usecase.auth

import com.rmakiyama.spatz.domain.model.auth.AuthUser
import com.rmakiyama.spatz.domain.repository.AuthRepository
import com.rmakiyama.spatz.core.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoadAuthUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : com.rmakiyama.spatz.usecase.FlowUseCase<Unit, AuthUser?>() {

    override fun execute(parameters: Unit): Flow<Result<AuthUser?>> {
        return authRepository.userFlow().map { Result.Success(it) }
    }
}
