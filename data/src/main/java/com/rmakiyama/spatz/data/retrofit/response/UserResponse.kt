package com.rmakiyama.spatz.data.retrofit.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "id")
    val id: Long,
    @Json(name = "id_str")
    val idStr: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "screen_name")
    val screenName: String,
    @Json(name = "location")
    val location: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "url")
    val url: String?,
    @Json(name = "entities")
    val entities: Entities,
    @Json(name = "protected")
    val isProtectedAccount: Boolean,
    @Json(name = "followers_count")
    val followersCount: Int,
    @Json(name = "friends_count")
    val friendsCount: Int,
    @Json(name = "listed_count")
    val listedCount: Int,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "favourites_count")
    val favouritesCount: Int,
    @Json(name = "verified")
    val verified: Boolean,
    @Json(name = "statuses_count")
    val statusesCount: Int,
    @Json(name = "profile_image_url")
    val profileImageUrl: String,
    @Json(name = "profile_image_url_https")
    val profileImageUrlHttps: String,
    @Json(name = "profile_banner_url")
    val profileBannerUrl: String?,
    @Json(name = "profile_link_color")
    val profileLinkColor: String,
    @Json(name = "profile_sidebar_border_color")
    val profileSidebarBorderColor: String,
    @Json(name = "profile_sidebar_fill_color")
    val profileSidebarFillColor: String,
    @Json(name = "profile_text_color")
    val profileTextColor: String,
    @Json(name = "profile_use_background_image")
    val profileUseBackgroundImage: Boolean,
    @Json(name = "has_extended_profile")
    val hasExtendedProfile: Boolean,
    @Json(name = "default_profile")
    val defaultProfile: Boolean,
    @Json(name = "default_profile_image")
    val defaultProfileImage: Boolean,
    @Json(name = "following")
    val following: Boolean,
    @Json(name = "follow_request_sent")
    val followRequestSent: Boolean,
    @Json(name = "notifications")
    val notifications: Boolean,
    @Json(name = "translator_type")
    val translatorType: String
)
