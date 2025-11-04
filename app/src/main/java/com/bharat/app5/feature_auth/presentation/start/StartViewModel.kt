package com.bharat.app5.feature_auth.presentation.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bharat.app5.feature_auth.domain.usecase.LoginUserUseCase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

data class LoginUiState(
    val isLoggingIn : Boolean = false,
    val loginError : String? = null,
    val loginSuccess : Boolean = false
)

@HiltViewModel
class StartViewModel @Inject constructor(
    private val loginUserUseCaseProvider : Provider<LoginUserUseCase>,
) : ViewModel() {
    private val loginUserUseCase by lazy { loginUserUseCaseProvider.get() }

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()



    fun onLocalGoogleSignInError(error : String){
        _uiState.update {
            it.copy(isLoggingIn = false, loginError = error, loginSuccess = false)
        }
    }

    fun onLocalGoogleSignInSuccess(idToken : String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        submitLogin(credential)
    }


    fun onLocalGoogleSignInErrorDismiss(){
        _uiState.update {
            it.copy(isLoggingIn = false, loginError = null, loginSuccess = false)
        }
    }

    private fun submitLogin(credential : AuthCredential){
        viewModelScope.launch {
            loginUserUseCase(credential)
                .onStart {
                    _uiState.update {
                        it.copy(isLoggingIn = true, loginError = null, loginSuccess = false)
                    }
                }
                .collect { result ->
                    result.fold(
                        onSuccess = {
                            _uiState.update {
                                it.copy(isLoggingIn = false, loginError = null, loginSuccess = true)
                            }
                        },
                        onFailure = { e ->
                            _uiState.update {
                                it.copy(isLoggingIn = false, loginError = e.localizedMessage, loginSuccess = false)
                            }

                        }
                    )
                }

        }
    }


}