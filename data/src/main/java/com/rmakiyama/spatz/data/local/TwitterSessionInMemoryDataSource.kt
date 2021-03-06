package com.rmakiyama.spatz.data.local

import com.rmakiyama.spatz.data.TwitterSessionSource
import com.rmakiyama.spatz.domain.model.twitter.TwitterSession
import javax.inject.Inject

// debug
class TwitterSessionInMemoryDataSource @Inject constructor() : TwitterSessionSource {

    private var session: TwitterSession? = null

    override fun save(session: TwitterSession) {
        this.session = session
    }

    override fun get() = session

    override fun clear() {
        session = null
    }
}
