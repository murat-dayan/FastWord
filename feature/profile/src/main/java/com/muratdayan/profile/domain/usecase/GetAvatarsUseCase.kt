package com.muratdayan.profile.domain.usecase

import com.muratdayan.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class GetAvatarsUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    operator fun invoke(folderName:String) = profileRepository.getAvatars(folderName)
}