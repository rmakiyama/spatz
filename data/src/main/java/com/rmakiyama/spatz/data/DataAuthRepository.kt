package com.rmakiyama.spatz.data

import com.rmakiyama.spatz.domain.model.auth.AuthUser
import com.rmakiyama.spatz.domain.repository.AuthRepository
import com.rmakiyama.spatz.domain.result.Result
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
internal class DataAuthRepository @Inject constructor(
    private val localDataSource: AuthDataSource
) : AuthRepository {

    private val _authUser = ConflatedBroadcastChannel<Result<AuthUser?>>()
    private val authUser get() = _authUser.asFlow()

    override fun userFlow(): Flow<Result<AuthUser?>> = flow {
        emit(localDataSource.get())
        emitAll(authUser)
    }

    override suspend fun save(user: AuthUser) {
        localDataSource.save(user)
        _authUser.send(Result.Success(user))
    }
}
