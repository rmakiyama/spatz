package com.rmakiyama.spatz.domain.usecase

import com.rmakiyama.spatz.domain.model.Tweet
import com.rmakiyama.spatz.domain.repository.TweetRepository
import javax.inject.Inject

internal class GetTweetsUseCaseImpl @Inject constructor(
    private val tweetRepository: TweetRepository
) : GetTweetsUseCase {

    override suspend fun run(command: Unit): List<Tweet> {
        return tweetRepository.getTweets()
    }
}
