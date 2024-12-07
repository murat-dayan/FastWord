package com.muratdayan.auth.domain.repository

import com.muratdayan.common.AppError
import com.muratdayan.common.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun guestSignIn(): Flow<Result<Boolean, AppError>>
}