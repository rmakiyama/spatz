package com.rmakiyama.spatz.domain.repository

import com.rmakiyama.spatz.domain.model.auth.AuthUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun userFlow(): Flow<AuthUser?>

    suspend fun save(user: AuthUser)
}
