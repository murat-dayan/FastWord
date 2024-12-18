package com.muratdayan.domain.usecase

import com.muratdayan.domain.repository.UserDomainRepository
import javax.inject.Inject

class GetUserInfoDomainUseCase @Inject constructor(
    private val userDomainRepository: UserDomainRepository
) {
    operator fun invoke() = userDomainRepository.getUser()
}