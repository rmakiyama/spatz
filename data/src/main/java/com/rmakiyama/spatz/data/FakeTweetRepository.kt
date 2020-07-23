package com.rmakiyama.spatz.data

import com.rmakiyama.spatz.domain.model.Url
import com.rmakiyama.spatz.domain.model.tweet.Tweet
import com.rmakiyama.spatz.domain.model.tweet.TweetId
import com.rmakiyama.spatz.domain.model.tweet.TweetText
import com.rmakiyama.spatz.domain.model.user.User
import com.rmakiyama.spatz.domain.model.user.UserDescription
import com.rmakiyama.spatz.domain.model.user.UserId
import com.rmakiyama.spatz.domain.model.user.UserName
import com.rmakiyama.spatz.domain.model.user.UserScreenName
import com.rmakiyama.spatz.domain.repository.TweetRepository
import javax.inject.Inject

internal class FakeTweetRepository @Inject constructor() : TweetRepository {

    override suspend fun getTweets(): List<Tweet> {
        // TODO
        return listOf(
            Tweet(
                id = TweetId(value = "0"),
                text = TweetText(value = "hello"),
                user = User(
                    id = UserId(value = "1"),
                    name = UserName(value = "rmakiyama"),
                    screenName = UserScreenName(value = "_rmakiyama"),
                    userWebUrl = Url("https://rmakiyama.com"),
                    profilePhotoUrl = Url(value = "https://pbs.twimg.com/profile_images/792423295479984128/FSVOAqna_400x400.jpg"),
                    profileBannerUrl = Url(value = "https://pbs.twimg.com/profile_banners/2828421925/1512595099/1500x500"),
                    description = null,
                    followersCount = 110,
                    followsCount = 120,
                    favoritesCount = 124
                ),
                createdTimestamp = 1595501426
            ),
            Tweet(
                id = TweetId(value = "1"),
                text = TweetText(value = "world"),
                user = User(
                    id = UserId(value = "2"),
                    name = UserName(value = "rmakiyama2"),
                    screenName = UserScreenName(value = "_rmakiyama2"),
                    userWebUrl = Url("https://rmakiyama.com"),
                    profilePhotoUrl = Url(value = "https://pbs.twimg.com/profile_images/792423295479984128/FSVOAqna_400x400.jpg"),
                    profileBannerUrl = Url(value = "https://pbs.twimg.com/profile_banners/2828421925/1512595099/1500x500"),
                    description = UserDescription("hello!"),
                    followersCount = 222,
                    followsCount = 222,
                    favoritesCount = 222
                ),
                createdTimestamp = 1595501426
            ),
            Tweet(
                id = TweetId(value = "2"),
                text = TweetText(value = "!!!!!!!"),
                user = User(
                    id = UserId(value = "3"),
                    name = UserName(value = "rmakiyama3"),
                    screenName = UserScreenName(value = "_rmakiyama3"),
                    userWebUrl = Url("https://rmakiyama.com"),
                    profilePhotoUrl = Url(value = "https://pbs.twimg.com/profile_images/792423295479984128/FSVOAqna_400x400.jpg"),
                    profileBannerUrl = Url(value = "https://pbs.twimg.com/profile_banners/2828421925/1512595099/1500x500"),
                    description = null,
                    followersCount = 333,
                    followsCount = 333,
                    favoritesCount = 333
                ),
                createdTimestamp = 1595501426
            )
        )
    }
}
