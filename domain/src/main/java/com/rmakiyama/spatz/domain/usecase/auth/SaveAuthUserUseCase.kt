package com.rmakiyama.spatz.domain.usecase.auth

import com.rmakiyama.spatz.domain.model.auth.AuthUser
import com.rmakiyama.spatz.domain.usecase.SuspendUseCase

interface SaveAuthUserUseCase : SuspendUseCase<AuthUser, Unit>
