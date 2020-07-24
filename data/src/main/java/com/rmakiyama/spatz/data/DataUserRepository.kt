package com.rmakiyama.spatz.data

import com.rmakiyama.spatz.domain.model.user.User
import com.rmakiyama.spatz.domain.model.user.UserId
import com.rmakiyama.spatz.domain.repository.UserRepository
import javax.inject.Inject

internal class DataUserRepository @Inject constructor(
    private val localDataSource: UserDataSource
) : UserRepository {

    override suspend fun save(user: User) {
        localDataSource.save(user)
    }

    override suspend fun find(userId: UserId): User? {
        return localDataSource.findById(userId)
    }
}
