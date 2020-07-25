package com.rmakiyama.spatz.domain.usecase

interface SuspendUseCase<in Command, out Type> {

    suspend fun run(command: Command): Type
}
