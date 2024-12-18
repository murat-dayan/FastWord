package com.muratdayan.domain.usecase

import com.muratdayan.common.AppError
import com.muratdayan.common.Result
import com.muratdayan.common.StatType
import com.muratdayan.domain.repository.UserStatsDomainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateStatsDomainUseCase @Inject constructor(
    private val userStatsDomainRepository: UserStatsDomainRepository
) {
    fun updateStat(statType: StatType, newValue:Int) : Flow<Result<Unit, AppError>> =
        when(statType){
            StatType.TOKEN -> {
                userStatsDomainRepository.updateToken(newValue)
            }
            StatType.ENERGY -> {
                userStatsDomainRepository.updateEnergy(newValue)
            }
            StatType.EMERALD -> {
                userStatsDomainRepository.updateEmerald(newValue)
            }
        }


}