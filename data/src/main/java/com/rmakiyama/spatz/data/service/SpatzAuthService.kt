package com.rmakiyama.spatz.data.service

import com.rmakiyama.spatz.data.retrofit.TwitterApiClient
import com.rmakiyama.spatz.domain.service.AuthService
import javax.inject.Inject

internal class SpatzAuthService @Inject constructor(
    private val api: TwitterApiClient
) : AuthService {

    override suspend fun login() {
        api.requestToken()
    }
}
