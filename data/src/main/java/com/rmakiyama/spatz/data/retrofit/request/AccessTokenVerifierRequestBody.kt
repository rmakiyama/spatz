package com.rmakiyama.spatz.data.retrofit.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccessTokenVerifierRequestBody(
    @Json(name = "oauth_verifier")
    val verifier: String
)
