package com.rmakiyama.spatz.data

import com.rmakiyama.spatz.domain.model.twitter.RequestToken

interface RequestTokenSource {
    fun save(requestToken: RequestToken)
    fun get(): RequestToken?
    fun clear()
}
