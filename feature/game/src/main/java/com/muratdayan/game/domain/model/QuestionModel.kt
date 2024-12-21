package com.muratdayan.game.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class QuestionModel(
    val id: String? = null,
    val question_text:String,
    val created_at:String? = null
)