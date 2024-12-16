package com.muratdayan.domain.usecase

import com.muratdayan.domain.repository.UsersDomainRepository
import javax.inject.Inject

class GetUserStatsDomainUseCase @Inject constructor(
    private val usersDomainRepository: UsersDomainRepository
){
    operator fun invoke() = usersDomainRepository.getUserStats()
}