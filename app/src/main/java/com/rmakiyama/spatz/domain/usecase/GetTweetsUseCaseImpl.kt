package com.rmakiyama.spatz.domain.usecase

import com.rmakiyama.spatz.domain.model.Tweet
import com.rmakiyama.spatz.domain.model.TweetId
import com.rmakiyama.spatz.domain.model.TweetText

class GetTweetsUseCaseImpl() : GetTweetsUseCase {
    override suspend fun run(command: Unit): List<Tweet> {
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
