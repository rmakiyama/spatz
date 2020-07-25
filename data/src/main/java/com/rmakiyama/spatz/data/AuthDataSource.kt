package com.rmakiyama.spatz.data

import com.rmakiyama.spatz.domain.model.auth.AuthUser

interface AuthDataSource {
    suspend fun save(user: AuthUser)
    suspend fun get(): AuthUser?
}
