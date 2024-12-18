package com.muratdayan.friends.domain.usecase

import com.muratdayan.friends.domain.repository.FriendsRepository
import javax.inject.Inject

class GetPendingFriendsUseCase @Inject constructor(
    private val friendsRepository: FriendsRepository

) {

    operator fun invoke() = friendsRepository.getPendingFriends()
}