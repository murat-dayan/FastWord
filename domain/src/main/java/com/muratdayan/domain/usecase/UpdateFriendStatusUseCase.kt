package com.muratdayan.domain.usecase

import com.muratdayan.domain.repository.FriendsDomainRepository
import javax.inject.Inject

class UpdateFriendStatusUseCase @Inject constructor(
    private val friendsDomainRepository: FriendsDomainRepository
) {
    operator fun invoke(statusValue: String, friendId: String) =
        friendsDomainRepository.updateFriendStatus(statusValue, friendId)
}