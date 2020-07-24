package com.rmakiyama.spatz.domain.usecase

import com.rmakiyama.spatz.domain.model.tweet.Tweet
import com.rmakiyama.spatz.domain.repository.TweetRepository
import com.rmakiyama.spatz.domain.repository.UserRepository
import javax.inject.Inject

internal class GetTweetsUseCaseImpl @Inject constructor(
    private val tweetRepository: TweetRepository,
    private val userRepository: UserRepository
) : GetTweetsUseCase {

    override suspend fun run(command: Unit): List<Tweet> {
        return tweetRepository.getTweets().also { tweets: List<Tweet> ->
            tweets.forEach { tweet -> userRepository.save(tweet.user) }
        }
    }
}
