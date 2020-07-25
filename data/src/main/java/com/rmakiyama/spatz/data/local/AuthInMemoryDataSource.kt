package com.rmakiyama.spatz.data.local

import com.rmakiyama.spatz.data.AuthDataSource
import com.rmakiyama.spatz.domain.model.auth.AuthUser
import com.rmakiyama.spatz.domain.result.Result
import javax.inject.Inject

// debug
class AuthInMemoryDataSource @Inject constructor() : AuthDataSource {

    private var authUser: AuthUser? = null

    override suspend fun save(user: AuthUser) {
        authUser = user
    }

    override suspend fun get() = Result.Success(authUser)
}
