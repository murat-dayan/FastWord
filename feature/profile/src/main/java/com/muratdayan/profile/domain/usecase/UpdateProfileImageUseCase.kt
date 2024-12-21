package com.muratdayan.profile.domain.usecase

import com.muratdayan.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class UpdateProfileImageUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    operator fun invoke(imageUri:String) = profileRepository.updateProfileImage(imageUri)
}