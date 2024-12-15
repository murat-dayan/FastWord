package com.muratdayan.settings.domain.usecase

import com.muratdayan.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class ExitAppUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
){
    operator fun invoke() = settingsRepository.exitApp()
}