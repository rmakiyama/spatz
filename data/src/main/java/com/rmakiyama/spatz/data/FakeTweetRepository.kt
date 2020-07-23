package com.rmakiyama.spatz.data

import com.rmakiyama.spatz.domain.model.tweet.Tweet
import com.rmakiyama.spatz.domain.model.tweet.TweetId
import com.rmakiyama.spatz.domain.model.tweet.TweetText
import com.rmakiyama.spatz.domain.repository.TweetRepository
import javax.inject.Inject

internal class FakeTweetRepository @Inject constructor() : TweetRepository {

    override suspend fun getTweets(): List<Tweet> {
        // TODO
        return listOf(
            Tweet(
                id = TweetId(value = 0),
                text = TweetText(value = "hello"),
                createdTimestamp = 1595501426
            ),
            Tweet(
                id = TweetId(value = 1),
                text = TweetText(value = "world"),
                createdTimestamp = 1595501426
            ),
            Tweet(
                id = TweetId(value = 2),
                text = TweetText(value = "!!!!!!!"),
                createdTimestamp = 1595501426
            )
        )
    }
}
