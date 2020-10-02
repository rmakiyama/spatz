package com.rmakiyama.spatz.data.retrofit.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TweetResponse(
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "id")
    val id: Long,
    @Json(name = "id_str")
    val idStr: String,
    @Json(name = "text")
    val text: String,
    @Json(name = "truncated")
    val truncated: Boolean,
    @Json(name = "extended_entities")
    val extendedEntities: ExtendedEntities?,
    @Json(name = "source")
    val source: String,
    @Json(name = "in_reply_to_status_id")
    val inReplyToStatusId: Long?,
    @Json(name = "in_reply_to_status_id_str")
    val inReplyToStatusIdStr: String?,
    @Json(name = "in_reply_to_user_id")
    val inReplyToUserId: Long?,
    @Json(name = "in_reply_to_user_id_str")
    val inReplyToUserIdStr: String?,
    @Json(name = "in_reply_to_screen_name")
    val inReplyToScreenName: String?,
    @Json(name = "user")
    val user: UserResponse,
    @Json(name = "retweeted_status")
    val retweetedStatus: TweetResponse?,
    @Json(name = "is_quote_status")
    val isQuoteStatus: Boolean,
    @Json(name = "retweet_count")
    val retweetCount: Int,
    @Json(name = "favorite_count")
    val favoriteCount: Int,
    @Json(name = "favorited")
    val favorited: Boolean,
    @Json(name = "retweeted")
    val retweeted: Boolean,
    @Json(name = "lang")
    val lang: String
)
