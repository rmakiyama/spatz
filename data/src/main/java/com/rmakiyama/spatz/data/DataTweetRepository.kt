package com.rmakiyama.spatz.data

import com.rmakiyama.spatz.data.retrofit.TwitterApiClient
import com.rmakiyama.spatz.data.retrofit.mapper.toTweet
import com.rmakiyama.spatz.domain.model.tweet.Tweet
import com.rmakiyama.spatz.domain.repository.TweetRepository
import javax.inject.Inject

internal class DataTweetRepository @Inject constructor(
    private val api: TwitterApiClient
) : TweetRepository {

    override suspend fun getTweets(): List<Tweet> {
        val response = api.getHomeTimeline()
        if (response.isSuccessful) {
            return response.body().orEmpty().map { tweetResponse ->
                tweetResponse.toTweet()
            }
        } else {
            throw Throwable(response.errorBody()?.string())
        }
    }
}
