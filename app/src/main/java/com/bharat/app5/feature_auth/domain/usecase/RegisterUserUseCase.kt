package com.bharat.app5.feature_auth.domain.usecase

import androidx.credentials.Credential
import com.bharat.app5.feature_auth.domain.repository.AuthRepository
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.Flow

class RegisterUserUseCase(
    private val repository : AuthRepository
) {
    suspend operator fun invoke(credential: AuthCredential) : Flow<Result<String>>{
        return repository.firebaseSignInWithGoogle(credential)

    }
}