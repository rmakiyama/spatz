package com.rmakiyama.spatz.domain.repository

import com.rmakiyama.spatz.domain.model.Tweet
import kotlinx.coroutines.flow.Flow

interface TweetRepository {

    suspend fun getTweets(): List<Tweet>
}
