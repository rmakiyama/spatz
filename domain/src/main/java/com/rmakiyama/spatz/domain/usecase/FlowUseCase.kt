package com.rmakiyama.spatz.domain.usecase

import kotlinx.coroutines.flow.Flow

interface FlowUseCase<in Command, out Type> {

    fun load(command: Command): Flow<Type>
}
