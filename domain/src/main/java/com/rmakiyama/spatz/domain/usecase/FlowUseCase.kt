package com.rmakiyama.spatz.domain.usecase

import com.rmakiyama.spatz.domain.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

abstract class FlowUseCase<in Command, out Type> {

    operator fun invoke(parameters: Command): Flow<Result<Type>> = execute(parameters)
        .catch { e -> emit(Result.Error(Exception(e))) }

    protected abstract fun execute(parameters: Command): Flow<Result<Type>>
}
