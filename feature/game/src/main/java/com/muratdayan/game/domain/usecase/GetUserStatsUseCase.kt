package com.muratdayan.game.domain.usecase

import com.muratdayan.game.domain.repository.GameRepository
import javax.inject.Inject

class GetUserStatsUseCase @Inject constructor(
    private val gameRepository: GameRepository
){
    operator fun invoke() = gameRepository.getUserStats()
}