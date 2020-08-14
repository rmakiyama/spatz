package com.rmakiyama.spatz.data.retrofit

import retrofit2.http.POST

interface TwitterApiClient {

    @POST("oauth/request_token")
    suspend fun requestToken()
}
