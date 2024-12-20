package com.muratdayan.game.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.domain.model.UserDataModel
import com.muratdayan.domain.usecase.GetUserInfoDomainUseCase
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch


abstract class BaseViewModel(
    private val getUserInfoDomainUseCase: GetUserInfoDomainUseCase
) : ViewModel(){


    protected suspend fun fetchUserInfo(): UserDataModel? {
        return try {
            // getUserInfoDomainUseCase.invoke() asenkron bir işlem yapıyor ve sonucu döndürüyor
            val result = getUserInfoDomainUseCase.invoke().single()
            when (result) {
                is com.muratdayan.common.Result.Success -> result.data
                is com.muratdayan.common.Result.Error -> null
            }
        } catch (e: Exception) {
            null
        }
    }
}