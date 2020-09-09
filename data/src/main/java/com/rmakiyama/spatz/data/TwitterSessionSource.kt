package com.rmakiyama.spatz.data

import com.rmakiyama.spatz.domain.model.twitter.TwitterSession

interface TwitterSessionSource {
    fun save(session: TwitterSession)
    fun get(): TwitterSession?
}
