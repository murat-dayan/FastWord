package com.muratdayan.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class FriendsViewModel(
    val user_id: String,
    val friend_id: String,
    val friendship_status: String,
    val user_name: String,
    val avatar_uri: String
)
