package com.muratdayan.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDataModel(
    val id: String,
    val user_name: String,
    val avatar_uri: String?=null,
)
