package com.rmakiyama.spatz.data.local

import com.rmakiyama.spatz.data.RequestTokenSource
import com.rmakiyama.spatz.domain.model.twitter.RequestToken
import javax.inject.Inject

internal class RequestTokenInMemoryDataSource @Inject constructor() : RequestTokenSource {

    var requestToken: RequestToken? = null

    override fun save(requestToken: RequestToken) {
        this.requestToken = requestToken
    }

    override fun get(): RequestToken? {
        return requestToken
    }

    override fun clear() {
        requestToken = null
    }
}
