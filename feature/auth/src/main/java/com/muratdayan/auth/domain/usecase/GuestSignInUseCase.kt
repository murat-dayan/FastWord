package com.muratdayan.auth.domain.usecase

import com.muratdayan.auth.domain.repository.AuthRepository
import javax.inject.Inject

class GuestSignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke() = authRepository.guestSignIn()
}