package com.rmakiyama.spatz.data.retrofit.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Media(
    @Json(name = "id")
    val id: Long,
    @Json(name = "id_str")
    val idStr: String,
    @Json(name = "media_url")
    val mediaUrl: String,
    @Json(name = "media_url_https")
    val mediaUrlHttps: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "display_url")
    val displayUrl: String,
    @Json(name = "expanded_url")
    val expandedUrl: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "sizes")
    val sizes: Sizes
)
