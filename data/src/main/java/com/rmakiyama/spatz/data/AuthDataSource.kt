package com.rmakiyama.spatz.data

import com.rmakiyama.spatz.domain.model.auth.AuthUser
import com.rmakiyama.spatz.domain.result.Result

interface AuthDataSource {
    suspend fun save(user: AuthUser)
    suspend fun get(): Result<AuthUser?>
}
