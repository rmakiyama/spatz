package com.rmakiyama.spatz.data.local

import com.rmakiyama.spatz.data.UserDataSource
import com.rmakiyama.spatz.domain.model.user.User
import com.rmakiyama.spatz.domain.model.user.UserId
import javax.inject.Inject

class UserInMemoryDataSource @Inject constructor() : UserDataSource {

    private val _users = mutableMapOf<UserId, User>()

    override suspend fun save(user: User) {
        _users[user.id] = user
    }

    override suspend fun findById(userId: UserId): User? {
        return _users[userId]
    }
}
