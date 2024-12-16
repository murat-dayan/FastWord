package com.muratdayan.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class FriendsDataModel(
    val friend_id:String,
    val user:UserDataModel,
)