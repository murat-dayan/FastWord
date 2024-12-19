package com.muratdayan.domain.mapper

import com.muratdayan.domain.model.AcceptedFriendsModel
import com.muratdayan.domain.model.FriendsDataModel
import com.muratdayan.domain.model.RequestedFriendsModel

fun AcceptedFriendsModel.toFriendsDataModel() : FriendsDataModel{
    return FriendsDataModel(
        id = user_id,
        user = user,
        status = status
    )
}

fun RequestedFriendsModel.toFriendsDataModel() : FriendsDataModel {
    return FriendsDataModel(
        id = friend_id,
        user = user,
        status = status
    )
}