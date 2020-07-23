package com.rmakiyama.spatz.domain.usecase

interface UseCase<in Command, out Type> {

    suspend fun run(command: Command): Type
}
