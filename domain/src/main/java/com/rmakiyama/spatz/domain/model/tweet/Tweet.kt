package com.rmakiyama.spatz.domain.model.tweet

import com.rmakiyama.spatz.domain.model.user.User

data class Tweet(
    val id: TweetId,
    val text: TweetText,
    val user: User,
    val createdTimestamp: Long
)
