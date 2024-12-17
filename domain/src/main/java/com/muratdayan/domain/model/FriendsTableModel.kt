package com.muratdayan.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class FriendsTableModel(
    val id:String?=null,
    val user_id:String,
    val friend_id:String,
    val status:String,
    val created_at:String?=null ,
)
