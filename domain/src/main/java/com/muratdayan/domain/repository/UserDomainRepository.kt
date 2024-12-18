package com.muratdayan.domain.repository

import com.muratdayan.common.AppError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.UserDataModel
import kotlinx.coroutines.flow.Flow

interface UserDomainRepository {
    fun getUser(userId:String?=null): Flow<Result<UserDataModel,AppError>>
}