package com.bharat.app5.feature_auth.presentation.register

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharat.app5.app_root.AuthOperationState
import com.bharat.app5.feature_auth.domain.model.Gender
import com.bharat.app5.feature_auth.domain.model.Goal
import com.bharat.app5.feature_auth.domain.model.UserDetails
import com.bharat.app5.feature_auth.domain.usecase.RegisterUserUseCase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Provider


enum class RegistrationStep {
    GENDER_STEP,
    GOAL_STEP,
    NAME_STEP,
    DOB_STEP,
    HEIGHT_STEP,
    WEIGHT_STEP,
    AUTH_STEP,

}




data class RegisterUiState @RequiresApi(Build.VERSION_CODES.O) constructor(
    val isRegistering : Boolean = false,
    val registrationError : String? = null,
    val registrationSuccess : Boolean = false,
    val userDetails : UserDetails = UserDetails(),
    val currentStep : RegistrationStep = RegistrationStep.GENDER_STEP
)




@HiltViewModel
class StartViewModel @Inject constructor(
    private val registerUserUseCaseProvider : Provider<RegisterUserUseCase>,
    private val auth : FirebaseAuth,
    private val authOperationState: AuthOperationState
) : ViewModel() {
    private val registerUserUseCase by lazy { registerUserUseCaseProvider.get() }

    //UiState
    @RequiresApi(Build.VERSION_CODES.O)
    private val _uiState = MutableStateFlow(RegisterUiState())
    @RequiresApi(Build.VERSION_CODES.O)
    val uiState = _uiState.asStateFlow()


    //Unsupported goal
    private val _unSupportedGoal = MutableStateFlow<Goal?>(null)
    val unSupportedGoal = _unSupportedGoal.asStateFlow()






    @RequiresApi(Build.VERSION_CODES.O)
    fun onGenderSelected(gender : Gender){
        _uiState.update{ it.copy(userDetails = it.userDetails.copy(gender = gender))}
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun onGoalSelected(goal : Goal){
        if(goal !== Goal.LOSE_WEIGHT){
            _unSupportedGoal.value = goal
        } else {
            _uiState.update { it.copy(userDetails = it.userDetails.copy(goal = goal)) }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onDismissUnsupportedGoal(){
        _uiState.update { it.copy(userDetails = it.userDetails.copy(goal = Goal.LOSE_WEIGHT)) }
        _unSupportedGoal.value = null

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun onNameChanged(name : String){
        _uiState.update { it.copy(userDetails = it.userDetails.copy(name = name)) }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun onDobChanged(dob : LocalDate){
        _uiState.update { it.copy(userDetails = it.userDetails.copy(dob = dob)) }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun onHeightChanged(height : Double){
        _uiState.update { it.copy(userDetails = it.userDetails.copy(height = height)) }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun onWeightChanged(weight : Double){
        _uiState.update { it.copy(userDetails = it.userDetails.copy(weight = weight)) }
    }








    @RequiresApi(Build.VERSION_CODES.O)
    fun onLocalGoogleSignInError(error : String){
        _uiState.update { it.copy(isRegistering = false, registrationError = error, registrationSuccess = false) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onLocalGoogleSignInErrorDismiss(){
        _uiState.update { it.copy(isRegistering = false, registrationError = null, registrationSuccess = false) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onLocalGoogleSignInSuccess(idToken : String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        submitRegistration(credential)

    }






    @RequiresApi(Build.VERSION_CODES.O)
    private fun submitRegistration(credential: AuthCredential){
        viewModelScope.launch{
            authOperationState.setIsOperationInProgress(true)

            registerUserUseCase(credential, uiState.value.userDetails)
                .onStart {
                    _uiState.update { it.copy(isRegistering = true, registrationError = null, registrationSuccess = false) }
                }
                .collect { result ->
                result.fold(
                    onSuccess = {
                        _uiState.update { it.copy(isRegistering = false, registrationError = null, registrationSuccess = true) }
                        authOperationState.setIsOperationInProgress(false)
                    },
                    onFailure = { e ->
                        _uiState.update { it.copy(isRegistering = false, registrationError = e.localizedMessage ?: "Something Went Wrong", registrationSuccess = false) }
                        authOperationState.setIsOperationInProgress(false)
                    }
                )
            }

        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun goToNextStep(){
        val nextStep = when(_uiState.value.currentStep){
            RegistrationStep.GENDER_STEP -> RegistrationStep.GOAL_STEP
            RegistrationStep.GOAL_STEP -> RegistrationStep.NAME_STEP
            RegistrationStep.NAME_STEP -> RegistrationStep.DOB_STEP
            RegistrationStep.DOB_STEP -> RegistrationStep.HEIGHT_STEP
            RegistrationStep.HEIGHT_STEP -> RegistrationStep.WEIGHT_STEP
            RegistrationStep.WEIGHT_STEP -> RegistrationStep.AUTH_STEP
            RegistrationStep.AUTH_STEP -> null
        }
        if(nextStep != null){
            _uiState.update{ it.copy(currentStep = nextStep)}
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun goToPreviousStep(){
        val previouStep = when(_uiState.value.currentStep) {
            RegistrationStep.GENDER_STEP -> null
            RegistrationStep.GOAL_STEP -> RegistrationStep.GENDER_STEP
            RegistrationStep.NAME_STEP -> RegistrationStep.GOAL_STEP
            RegistrationStep.DOB_STEP -> RegistrationStep.NAME_STEP
            RegistrationStep.HEIGHT_STEP -> RegistrationStep.DOB_STEP
            RegistrationStep.WEIGHT_STEP -> RegistrationStep.HEIGHT_STEP
            RegistrationStep.AUTH_STEP ->  RegistrationStep.WEIGHT_STEP
        }
        if(previouStep != null){
            _uiState.update { it.copy(currentStep = previouStep) }
        }

    }





}