package com.bharat.app5.feature_auth.domain.repository

import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun firebaseSignInWithGoogle(credential : AuthCredential) : Flow<Result<String>>
}