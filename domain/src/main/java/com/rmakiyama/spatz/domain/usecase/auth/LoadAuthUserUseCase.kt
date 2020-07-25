package com.rmakiyama.spatz.domain.usecase.auth

import com.rmakiyama.spatz.domain.model.auth.AuthUser
import com.rmakiyama.spatz.domain.usecase.FlowUseCase

interface LoadAuthUserUseCase :
    FlowUseCase<Unit, AuthUser?>
