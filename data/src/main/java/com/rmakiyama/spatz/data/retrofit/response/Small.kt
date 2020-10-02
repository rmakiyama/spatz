package com.rmakiyama.spatz.data.retrofit.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Small(
    @Json(name = "w")
    val w: Int,
    @Json(name = "h")
    val h: Int,
    @Json(name = "resize")
    val resize: String
)
