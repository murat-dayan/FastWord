package com.muratdayan.domain.usecase

import com.muratdayan.domain.repository.UserStatsDomainRepository
import javax.inject.Inject

class GetUserStatsDomainUseCase @Inject constructor(
    private val userStatsDomainRepository: UserStatsDomainRepository
){
    operator fun invoke() = userStatsDomainRepository.getUserStats()
}