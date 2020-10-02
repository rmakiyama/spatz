package com.rmakiyama.spatz.domain.model.twitter

data class TwitterSession(
    val authToken: TwitterAuthToken,
    val screenName: String
)
