package com.bharat.app5.app_root

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthOperationState @Inject constructor() {
    private val _isOperationInProgress = MutableStateFlow(false)
    val isOperationInProgress = _isOperationInProgress.asStateFlow()

    fun setIsOperationInProgress(newProgress : Boolean){
        _isOperationInProgress.update { newProgress }
    }


}