package com.bharat.app5.feature_auth.domain.usecase

import com.bharat.app5.feature_auth.domain.repository.AuthRepository
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
   private val authRepository : AuthRepository
) {
    suspend operator fun invoke(
        credential: AuthCredential
    ) : Flow<Result<Unit>>{
        return authRepository.signInWithGoogle(credential)
    }
}