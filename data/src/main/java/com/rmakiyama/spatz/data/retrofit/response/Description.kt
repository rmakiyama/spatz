package com.rmakiyama.spatz.data.retrofit.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Description(
    @Json(name = "urls")
    val urls: List<UrlInfo>
)
