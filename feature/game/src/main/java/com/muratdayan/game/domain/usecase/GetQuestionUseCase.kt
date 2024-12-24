package com.muratdayan.game.domain.usecase

import com.muratdayan.game.domain.repository.StartRepository
import javax.inject.Inject

class GetQuestionUseCase @Inject constructor(
    private val startRepository: StartRepository
) {
    operator fun invoke(questionId: String) = startRepository.getQuestion(questionId)
}