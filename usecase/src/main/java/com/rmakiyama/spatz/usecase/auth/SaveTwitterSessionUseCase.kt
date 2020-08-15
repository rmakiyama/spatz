package com.rmakiyama.spatz.usecase.auth

import com.rmakiyama.spatz.domain.model.twitter.TwitterAuthToken
import com.rmakiyama.spatz.domain.model.twitter.TwitterSession
import com.rmakiyama.spatz.domain.repository.TwitterSessionRepository
import com.rmakiyama.spatz.usecase.SuspendUseCase
import javax.inject.Inject

class SaveTwitterSessionUseCase @Inject constructor(
    private val twitterSessionRepository: TwitterSessionRepository
) : SuspendUseCase<SaveTwitterSessionCommand, Unit>() {

    override suspend fun execute(command: SaveTwitterSessionCommand) {
        val session = TwitterSession(
            authToken = TwitterAuthToken(
                token = requireNotNull(command.token),
                secret = requireNotNull(command.secret)
            ),
            screenName = requireNotNull(command.screenName)
        )
        twitterSessionRepository.save(session)
    }
}

data class SaveTwitterSessionCommand(
    val token: String?,
    val secret: String?,
    val screenName: String?
)
