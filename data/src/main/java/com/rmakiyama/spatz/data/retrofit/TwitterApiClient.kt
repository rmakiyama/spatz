package com.rmakiyama.spatz.data.retrofit

import com.rmakiyama.spatz.data.retrofit.response.TweetResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TwitterApiClient {

    @POST("oauth/request_token")
    suspend fun requestToken(): ResponseBody

    @FormUrlEncoded
    @POST("oauth/access_token")
    suspend fun accessToken(
        @Field("oauth_verifier") oauthVerifier: String
    ): ResponseBody

    @GET("1.1/statuses/home_timeline.json")
    suspend fun getHomeTimeline(
        @Query("count") count: Int = 30,
        @Query("since_id") sinceId: String? = null,
        @Query("max_id") maxId: String? = null,
        @Query("trim_user") trimUser: Boolean = false,
        @Query("exclude_replies") excludeReplies: Boolean = true,
        @Query("include_entities") includeEntities: Boolean = false
    ): Response<List<TweetResponse>>
}
