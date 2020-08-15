package com.rmakiyama.spatz.usecase.auth

import com.rmakiyama.spatz.domain.model.twitter.RequestToken
import com.rmakiyama.spatz.domain.service.AuthService
import com.rmakiyama.spatz.usecase.SuspendUseCase
import javax.inject.Inject

class TwitterLoginUseCase @Inject constructor(
    private val authService: AuthService
) : SuspendUseCase<Unit, RequestToken>() {

    override suspend fun execute(command: Unit): RequestToken {
        return authService.login()
    }
}
