package com.rmakiyama.spatz.usecase.tweet

import com.rmakiyama.spatz.domain.model.tweet.Tweet
import com.rmakiyama.spatz.domain.repository.TweetRepository
import com.rmakiyama.spatz.domain.repository.UserRepository
import com.rmakiyama.spatz.usecase.SuspendUseCase
import javax.inject.Inject

class GetTweetsUseCase @Inject constructor(
    private val tweetRepository: TweetRepository,
    private val userRepository: UserRepository
) : SuspendUseCase<Unit, List<Tweet>>() {

    override suspend fun execute(command: Unit): List<Tweet> {
        return tweetRepository.getTweets().also { tweets: List<Tweet> ->
            tweets.forEach { tweet -> userRepository.save(tweet.user) }
        }
    }
}
