package com.bharat.app5.feature_auth.domain.usecase

import androidx.credentials.Credential
import com.bharat.app5.feature_auth.domain.model.UserDetails
import com.bharat.app5.feature_auth.domain.repository.AuthRepository
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repository : AuthRepository
) {
    suspend operator fun invoke(
        credential: AuthCredential,
        userDetails: UserDetails
    ): Flow<Result<Unit>> {
        return repository.firebaseSignInWithGoogle(credential).flatMapConcat { result ->
            result.fold(
                onSuccess = { uid ->
                    repository.saveUsersDetails(uid, userDetails)
                },
                onFailure = { e ->
                    flowOf(Result.failure(e))
                }
            )

        }
    }
}