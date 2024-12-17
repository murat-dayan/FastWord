package com.muratdayan.profile.domain.usecase

import com.muratdayan.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class CheckUserTypeUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    operator fun invoke(userId:String) = profileRepository.checkUserType(userId)
}