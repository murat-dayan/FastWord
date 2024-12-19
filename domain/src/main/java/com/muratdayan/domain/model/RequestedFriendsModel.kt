package com.muratdayan.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RequestedFriendsModel(
    val friend_id:String,
    val user:UserDataModel,
    val status:String,
)
