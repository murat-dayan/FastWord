package com.muratdayan.game.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RoomRoundModel(
    val id:String?=null,
    val room_id:String,
    val round_number:Int,
    val winner_id:String?=null,
    val player_one_score:Int,
    val player_two_score:Int,
    val created_at:String?=null,
    val question_id:String
)
