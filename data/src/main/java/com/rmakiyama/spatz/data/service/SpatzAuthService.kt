package com.rmakiyama.spatz.data.service

import com.rmakiyama.spatz.data.TwitterSessionSource
import com.rmakiyama.spatz.data.retrofit.TwitterApiClient
import com.rmakiyama.spatz.domain.model.twitter.RequestToken
import com.rmakiyama.spatz.domain.model.twitter.TwitterAuthToken
import com.rmakiyama.spatz.domain.model.twitter.TwitterSession
import com.rmakiyama.spatz.domain.service.AuthService
import okhttp3.ResponseBody
import javax.inject.Inject

internal class SpatzAuthService @Inject constructor(
    private val api: TwitterApiClient,
    private val sessionSource: TwitterSessionSource
) : AuthService {

    override suspend fun getRequestToken(): RequestToken {
        return api.requestToken().toOAuthToken().also { requestToken ->
            sessionSource.save(
                TwitterSession(
                    authToken = TwitterAuthToken(
                        token = requestToken.token,
                        secret = requestToken.tokenSecret
                    ),
                    screenName = ""
                )
            )
        }
    }

    override suspend fun getAccessToken(token: String, verifier: String) {
        checkNotNull(sessionSource.get()) { "you should request token before." }
        if (token == requireNotNull(sessionSource.get()).authToken.token) {
            try {
                api.accessToken(verifier).saveSession()
            } catch (e: Exception) {
                sessionSource.clear()
                throw e
            }
        } else {
            throw IllegalArgumentException("invalid token.")
        }
    }

    private fun ResponseBody.toOAuthToken(): RequestToken {
        val params = string().split("&")
            .map { value -> value.split("=", limit = 2) }
            .map { it[0] to it[1] }.toMap()
        val token = requireNotNull(params["oauth_token"])
        val tokenSecret = requireNotNull(params["oauth_token_secret"])
        return RequestToken(token, tokenSecret)
    }

    private fun ResponseBody.saveSession() {
        val params = string().split("&")
            .map { value -> value.split("=", limit = 4) }
            .map { it[0] to it[1] }.toMap()
        val token = requireNotNull(params["oauth_token"])
        val tokenSecret = requireNotNull(params["oauth_token_secret"])
        val screenName = requireNotNull(params["screen_name"])
        sessionSource.save(
            TwitterSession(
                authToken = TwitterAuthToken(
                    token = token,
                    secret = tokenSecret
                ),
                screenName = screenName
            )
        )
    }
}
