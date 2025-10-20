package com.bharat.app5.feature_auth.presentation.register

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.bharat.app5.feature_auth.domain.model.Gender
import com.bharat.app5.feature_auth.domain.model.Goal
import com.bharat.app5.feature_auth.domain.model.UserDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


enum class RegistrationStep {
    GENDER_STEP,
    GOAL_STEP,
    NAME_STEP,
    DOB_STEP,
    HEIGHT_STEP,
    WEIGHT_STEP,
    AUTH_STEP,
    EMAIL_AUTH_STEP,
}


data class RegisterUiState @RequiresApi(Build.VERSION_CODES.O) constructor(
    val isRegistering : Boolean = false,
    val registrationError : String? = null,
    val registrationSuccess : Boolean = false,
    val userDetails : UserDetails = UserDetails(),
    val  currentStep : RegistrationStep = RegistrationStep.GENDER_STEP
)


class RegisterViewModel : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val _uiState = MutableStateFlow(RegisterUiState())
    @RequiresApi(Build.VERSION_CODES.O)
    val uiState = _uiState.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun onGenderSelected(gender : Gender){
        _uiState.update{ it.copy(userDetails = it.userDetails.copy(gender = gender)}
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun onGoalSelected(goal : Goal){
        _uiState.update { it.copy(userDetails = it.userDetails.copy(goal = goal)) }
    }



}