package com.rmakiyama.spatz.domain.model

data class Tweet(
    val id: TweetId,
    val text: TweetText,
    val createdTimestamp: Long
)
