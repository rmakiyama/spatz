package com.rmakiyama.spatz.data

import com.rmakiyama.spatz.domain.model.twitter.TwitterSession
import com.rmakiyama.spatz.domain.repository.TwitterSessionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
internal class DataTwitterSessionRepository @Inject constructor(
    private val localDataSource: TwitterSessionSource
) : TwitterSessionRepository {

    private val _session = ConflatedBroadcastChannel<TwitterSession?>()
    private val session get() = _session.asFlow()

    override fun sessionFlow(): Flow<TwitterSession?> = flow {
        emit(localDataSource.get())
        emitAll(session)
    }

    override suspend fun save(session: TwitterSession) {
        localDataSource.save(session)
    }
}
