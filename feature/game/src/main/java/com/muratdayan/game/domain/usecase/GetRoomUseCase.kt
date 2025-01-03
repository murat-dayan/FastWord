package com.muratdayan.game.domain.usecase

import com.muratdayan.game.domain.repository.StartRepository
import javax.inject.Inject

class GetRoomUseCase @Inject constructor(
    private val startRepository: StartRepository
) {
    operator fun invoke(roomId: String) = startRepository.getRoom(roomId)
}