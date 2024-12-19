package com.muratdayan.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AcceptedFriendsModel(
    val user_id:String,
    val user:UserDataModel,
    val status:String,
)
