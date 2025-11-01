package com.bharat.app5.feature_auth.data.repository

import androidx.credentials.Credential
import com.bharat.app5.feature_auth.domain.model.UserDetails
import com.bharat.app5.feature_auth.domain.repository.AuthRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepositoryIml(
   private val auth : FirebaseAuth,
   private val firestore : FirebaseFirestore
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



   override suspend fun saveUsersDetails(
      uid: String,
      userDetails: UserDetails
   ): Flow<Result<Unit>> = flow {
      val userDetailsMap = mapOf(
         "gender" to userDetails.gender?.name,
         "goal" to userDetails.goal?.name,
         "name" to userDetails.name,
         "dob" to userDetails.dob.toString(),
         "height" to userDetails.height,
         "weight" to userDetails.weight
      )
      firestore.collection("users").document(uid).set(userDetailsMap).await()
      emit(Result.success(Unit))
   } .catch {
      emit(Result.failure(Exception(it)))
   }
}