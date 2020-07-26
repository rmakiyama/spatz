package com.rmakiyama.spatz.usecase

import com.rmakiyama.spatz.core.result.Result

abstract class SuspendUseCase<in Command, out Type> {

    suspend operator fun invoke(command: Command): Result<Type> {
        return try {
            Result.Success(execute(command))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    protected abstract suspend fun execute(command: Command): Type
}
