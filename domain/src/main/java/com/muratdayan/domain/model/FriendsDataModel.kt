package com.muratdayan.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class FriendsDataModel(
    val id:String,
    val user:UserDataModel,
    val status:String,
)
