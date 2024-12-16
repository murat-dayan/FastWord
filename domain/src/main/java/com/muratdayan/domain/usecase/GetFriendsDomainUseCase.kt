package com.muratdayan.domain.usecase

import com.muratdayan.domain.repository.DomainRepository
import javax.inject.Inject

class GetFriendsDomainUseCase @Inject constructor(
    private val domainRepository: DomainRepository
){
    operator fun invoke() = domainRepository.getFriends()
}