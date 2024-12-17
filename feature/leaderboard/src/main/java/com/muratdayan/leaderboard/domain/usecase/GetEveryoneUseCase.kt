package com.muratdayan.leaderboard.domain.usecase

import com.muratdayan.leaderboard.domain.repository.LeaderBoardRepository
import javax.inject.Inject

class GetEveryoneUseCase @Inject constructor(
    private val leaderBoardRepository: LeaderBoardRepository
) {

    operator fun invoke() = leaderBoardRepository.getEveryone()
}