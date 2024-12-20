package com.muratdayan.game.domain.usecase

import com.muratdayan.game.domain.repository.MatchRepository
import javax.inject.Inject

class FindOrCreateRoomUseCase @Inject constructor(
    private val matchRepository: MatchRepository
) {

    operator fun invoke(userId:String) = matchRepository.findOrCreateRoom(userId)
}