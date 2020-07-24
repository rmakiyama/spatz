package com.rmakiyama.spatz.data

import com.rmakiyama.spatz.domain.model.user.User
import com.rmakiyama.spatz.domain.model.user.UserId

interface UserDataSource {
    suspend fun save(user: User)
    suspend fun findById(userId: UserId): User?
}
