package com.muratdayan.auth.domain.usecase

import android.content.Context
import com.muratdayan.auth.domain.repository.AuthRepository
import javax.inject.Inject

class GetFacebookSignUrlUseCase @Inject constructor(
    private val authRepository: AuthRepository
)
{
    operator fun invoke() = authRepository.getFacebookSignInUrl()
}