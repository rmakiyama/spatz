package com.rmakiyama.spatz.domain.model.user

import com.rmakiyama.spatz.domain.model.ImageUrl

data class User(
    val id: UserId,
    val name: UserName,
    val screenName: UserScreenName,
    val userWebUrl: ImageUrl?,
    val profilePhotoUrl: ImageUrl,
    val profileBannerUrl: ImageUrl,
    val description: UserDescription?,
    val followersCount: Int,
    val followsCount: Int,
    val favoritesCount: Int
)
