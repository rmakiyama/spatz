package com.rmakiyama.spatz.domain.repository

import com.rmakiyama.spatz.domain.model.twitter.TwitterSession
import kotlinx.coroutines.flow.Flow

interface TwitterSessionRepository {

    fun sessionFlow(): Flow<TwitterSession?>

    suspend fun save(session: TwitterSession)
}
