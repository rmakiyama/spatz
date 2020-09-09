package com.rmakiyama.spatz.data.retrofit.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sizes(
    @Json(name = "thumb")
    val thumb: Thumb,
    @Json(name = "medium")
    val medium: Medium,
    @Json(name = "small")
    val small: Small,
    @Json(name = "large")
    val large: Large
)
