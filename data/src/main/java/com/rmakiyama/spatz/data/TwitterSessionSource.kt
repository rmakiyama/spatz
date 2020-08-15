package com.rmakiyama.spatz.data

import com.rmakiyama.spatz.domain.model.twitter.TwitterSession

interface TwitterSessionSource {
    suspend fun save(session: TwitterSession)
    suspend fun get(): TwitterSession?
}
