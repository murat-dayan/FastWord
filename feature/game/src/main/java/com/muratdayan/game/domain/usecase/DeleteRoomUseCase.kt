package com.muratdayan.game.domain.usecase

import com.muratdayan.game.domain.repository.MatchRepository
import javax.inject.Inject

class DeleteRoomUseCase @Inject constructor(
    private val matchRepository: MatchRepository
) {
    operator fun invoke(roomId:String) = matchRepository.deleteRoomUseCase(roomId)
}