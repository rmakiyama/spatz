package com.rmakiyama.spatz.domain.model.user

import com.rmakiyama.spatz.domain.model.Url

data class User(
    val id: UserId,
    val name: UserName,
    val screenName: UserScreenName,
    val userWebUrl: Url?,
    val profilePhotoUrl: Url,
    val profileBannerUrl: Url,
    val description: UserDescription?,
    val followersCount: Int,
    val followsCount: Int,
    val favoritesCount: Int
)
