package com.rmakiyama.spatz.domain.repository

import com.rmakiyama.spatz.domain.model.tweet.Tweet

interface TweetRepository {

    suspend fun getTweets(): List<Tweet>
}
