package com.rmakiyama.spatz.data.retrofit.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UrlInfo(
    @Json(name = "url")
    val url: String,
    @Json(name = "expanded_url")
    val expandedUrl: String,
    @Json(name = "display_url")
    val displayUrl: String
)
