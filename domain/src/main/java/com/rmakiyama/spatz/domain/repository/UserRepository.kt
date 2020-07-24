package com.rmakiyama.spatz.domain.repository

import com.rmakiyama.spatz.domain.model.user.User
import com.rmakiyama.spatz.domain.model.user.UserId

interface UserRepository {

    suspend fun save(user: User)
    suspend fun find(userId: UserId): User?
}
