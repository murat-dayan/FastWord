package com.muratdayan.domain.usecase

import com.muratdayan.domain.repository.FriendsDomainRepository
import javax.inject.Inject

class UpdateFriendStatusDomainUseCase @Inject constructor(
    private val friendsDomainRepository: FriendsDomainRepository
) {
    operator fun invoke(statusValue: String, senderId: String) =
        friendsDomainRepository.updateFriendStatus(statusValue, senderId = senderId)
}