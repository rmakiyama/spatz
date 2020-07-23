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

    @ExperimentalStdlibApi
    override suspend fun getTweets(): List<Tweet> {
        // TODO
        val list = listOf(
            Tweet(
                id = TweetId(value = "0"),
                text = TweetText(value = "RadiotalkではFragmentのthemeを切り替えちゃいたいときにもContextThemeWrapper使ってたりする\uD83D\uDC68\uD83C\uDFFB\u200D\uD83D\uDCBB\n画面単位で動的になときはViewをそれぞれ変えるよりthemeで制御したほうが楽かな〜と思ってやってるけど諸説ありそうなので各社の知見欲しい\uD83D\uDE4C\uD83C\uDFFB"),
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
                text = TweetText(value = "あらためて「ドメイン駆動設計入門 」を読んでいる。ドメインサービスの見極めが難しそうだなあ。実コードに落とし込んでコネコネしたくなってる。"),
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
                text = TweetText(value = "File > New > New Project..."),
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
        return buildList {
            addAll(list)
            addAll(list)
            addAll(list)
            addAll(list)
            addAll(list)
        }
    }
}
