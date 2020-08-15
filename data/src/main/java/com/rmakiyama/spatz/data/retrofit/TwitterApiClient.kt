package com.rmakiyama.spatz.data.retrofit

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.POST

interface TwitterApiClient {

    @POST("oauth/request_token")
    suspend fun requestToken(): ResponseBody
}
