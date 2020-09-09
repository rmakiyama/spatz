package com.rmakiyama.spatz.data.retrofit.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Entities(
    @Json(name = "url")
    val url: Url?,
    @Json(name = "description")
    val description: Description?
)
