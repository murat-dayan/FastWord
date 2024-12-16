package com.muratdayan.domain.usecase

import com.muratdayan.domain.repository.FriendsDomainRepository
import javax.inject.Inject

class GetFriendsDomainUseCase @Inject constructor(
    private val friendsDomainRepository: FriendsDomainRepository
){
    operator fun invoke() = friendsDomainRepository.getFriends()
}