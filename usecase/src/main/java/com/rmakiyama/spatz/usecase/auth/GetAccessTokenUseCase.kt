package com.rmakiyama.spatz.usecase.auth

import com.rmakiyama.spatz.domain.service.AuthService
import com.rmakiyama.spatz.usecase.SuspendUseCase
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val authService: AuthService
) : SuspendUseCase<GetAccessTokenCommand, Unit>() {

    override suspend fun execute(command: GetAccessTokenCommand) {
        authService.getAccessToken(command.token, command.verifier)
    }
}

data class GetAccessTokenCommand(
    val token: String,
    val verifier: String
)
