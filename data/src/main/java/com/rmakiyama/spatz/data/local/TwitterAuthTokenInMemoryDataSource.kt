package com.rmakiyama.spatz.data.local

import com.rmakiyama.spatz.data.TwitterAuthTokenSource
import com.rmakiyama.spatz.domain.model.twitter.TwitterAuthToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TwitterAuthTokenInMemoryDataSource @Inject constructor() : TwitterAuthTokenSource {

    private var token: TwitterAuthToken? = null

    override fun cache(token: TwitterAuthToken) {
        this.token = token
    }

    override fun getCache(): TwitterAuthToken? {
        return token
    }

    override fun clear() {
        token = null
    }
}
