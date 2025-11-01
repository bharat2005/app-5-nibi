package com.bharat.app5.feature_auth.data.repository

import androidx.credentials.Credential
import com.bharat.app5.feature_auth.domain.repository.AuthRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepositoryIml(
   private val auth : FirebaseAuth
) : AuthRepository {
   override suspend fun firebaseSignInWithGoogle(credential: AuthCredential) : Flow<Result<String>> = flow{
      val result = auth.signInWithCredential(credential).await()
      val uid = result.user?.uid
      if(uid != null){
         emit(Result.success(uid))
      } else {
         emit(Result.failure(Exception("Uid is null")))
      }
   }.catch {
      emit(Result.failure(Exception(it)))
   }
}