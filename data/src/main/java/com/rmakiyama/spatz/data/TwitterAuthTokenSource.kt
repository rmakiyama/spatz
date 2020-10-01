package com.rmakiyama.spatz.data

import com.rmakiyama.spatz.domain.model.twitter.TwitterAuthToken

interface TwitterAuthTokenSource {
    fun cache(token: TwitterAuthToken)
    fun getCache(): TwitterAuthToken?
    fun clear()
}
