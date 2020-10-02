package com.rmakiyama.spatz.usecase.auth

import com.rmakiyama.spatz.core.result.Result
import com.rmakiyama.spatz.domain.model.twitter.TwitterSession
import com.rmakiyama.spatz.domain.repository.TwitterSessionRepository
import com.rmakiyama.spatz.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoadTwitterSessionUseCase @Inject constructor(
    private val twitterSessionRepository: TwitterSessionRepository
) : FlowUseCase<Unit, TwitterSession?>() {

    override fun execute(parameters: Unit): Flow<Result<TwitterSession?>> {
        return twitterSessionRepository.sessionFlow().map { Result.Success(it) }
    }
}
