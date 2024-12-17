package com.muratdayan.profile.domain.usecase

import com.muratdayan.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class SendFriendRequestUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    operator fun invoke(friendId:String) = profileRepository.sendFriendRequest(friendId)
}