package com.rmakiyama.spatz.domain.service

import com.rmakiyama.spatz.domain.model.twitter.RequestToken

interface AuthService {
    suspend fun getRequestToken(): RequestToken
    suspend fun getAccessToken(token: String, verifier: String)
}
