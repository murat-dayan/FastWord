package com.muratdayan.game.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RoomModel(
    val id:String?=null,
    val player_one_id:String?=null,
    val player_two_id:String?=null,
    val status:String = "waiting",
    val is_room_ready : Boolean = false,
    val created_at:String? = null,

)

sealed class MatchResult{
    data class RoomFound(val room: RoomModel): MatchResult()
    data class RoomCreated(val room: RoomModel): MatchResult()
    data object NoAvailableRoom : MatchResult()
}
