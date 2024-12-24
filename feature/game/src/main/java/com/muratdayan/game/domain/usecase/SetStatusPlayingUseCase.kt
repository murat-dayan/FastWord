package com.muratdayan.game.domain.usecase

import com.muratdayan.game.domain.repository.MatchRepository
import javax.inject.Inject

class SetStatusPlayingUseCase @Inject constructor(
    private val matchRepository: MatchRepository
) {

    suspend operator fun invoke(roomId:String) = matchRepository.setStatusPlaying(roomId)
}