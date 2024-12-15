package com.muratdayan.settings.domain.repository

import com.muratdayan.common.AppError
import com.muratdayan.common.Result
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    fun exitApp(): Flow<Result<Boolean,AppError>>
}