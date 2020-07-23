package com.rmakiyama.spatz.domain.repository

import com.rmakiyama.spatz.domain.model.Tweet

interface TweetRepository {

    suspend fun getTweets(): List<Tweet>
}
