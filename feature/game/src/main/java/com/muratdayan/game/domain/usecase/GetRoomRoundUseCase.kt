package com.muratdayan.game.domain.usecase

import com.muratdayan.game.domain.repository.StartRepository
import javax.inject.Inject

class GetRoomRoundUseCase @Inject constructor(
    private val startRepository: StartRepository
) {

    operator fun invoke(roomRoundId:String) = startRepository.getRoomRound(roomRoundId)
}