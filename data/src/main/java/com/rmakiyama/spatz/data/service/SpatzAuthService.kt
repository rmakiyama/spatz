package com.rmakiyama.spatz.data.service

import com.rmakiyama.spatz.data.retrofit.TwitterApiClient
import com.rmakiyama.spatz.domain.model.twitter.RequestToken
import com.rmakiyama.spatz.domain.service.AuthService
import okhttp3.ResponseBody
import javax.inject.Inject

internal class SpatzAuthService @Inject constructor(
    private val api: TwitterApiClient
) : AuthService {

    override suspend fun login(): RequestToken {
        return api.requestToken().toOAuthToken()
    }

    private fun ResponseBody.toOAuthToken(): RequestToken {
        val params = string().split("&")
            .map { value -> value.split("=", limit = 2) }
            .map { it[0] to it[1] }.toMap()
        val token = requireNotNull(params["oauth_token"])
        val tokenSecret = requireNotNull(params["oauth_token_secret"])
        return RequestToken(token, tokenSecret)
    }
}
