package com.rmakiyama.spatz.domain.usecase

import com.rmakiyama.spatz.domain.model.auth.AuthUser

interface LoadAuthUserUseCase : FlowUseCase<Unit, AuthUser?>
