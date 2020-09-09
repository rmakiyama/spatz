package com.rmakiyama.spatz.data.retrofit.mapper

import com.rmakiyama.spatz.data.retrofit.response.TweetResponse
import com.rmakiyama.spatz.domain.model.Url
import com.rmakiyama.spatz.domain.model.tweet.Tweet
import com.rmakiyama.spatz.domain.model.tweet.TweetId
import com.rmakiyama.spatz.domain.model.tweet.TweetText
import com.rmakiyama.spatz.domain.model.user.User
import com.rmakiyama.spatz.domain.model.user.UserDescription
import com.rmakiyama.spatz.domain.model.user.UserId
import com.rmakiyama.spatz.domain.model.user.UserName
import com.rmakiyama.spatz.domain.model.user.UserScreenName
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale.US

fun TweetResponse.toTweet(): Tweet {
    return Tweet(
        id = TweetId(value = idStr),
        text = TweetText(value = text),
        user = User(
            id = UserId(value = user.idStr),
            name = UserName(value = user.name),
            screenName = UserScreenName(value = user.screenName),
            userWebUrl = user.url?.let(::Url),
            profilePhotoUrl = Url(value = user.profileImageUrlHttps),
            profileBannerUrl = user.profileBannerUrl?.let(::Url),
            description = UserDescription(user.description),
            followersCount = user.followersCount,
            followsCount = user.friendsCount,
            favoritesCount = user.favouritesCount
        ),
        createdTimestamp = createdAt.toLocalDateTime()
    )
}

private fun String.toLocalDateTime(): LocalDateTime {
    val format = "EEE MMM dd HH:mm:ss xx yyyy"
    val formatter = DateTimeFormatter.ofPattern(format, US)
    return LocalDateTime.from(formatter.parse(this))
}
