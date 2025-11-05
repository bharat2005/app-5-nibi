package com.bharat.app5.feature_auth.data.repository

import android.util.Log
import androidx.credentials.Credential
import com.bharat.app5.feature_auth.domain.model.UserDetails
import com.bharat.app5.feature_auth.domain.repository.AuthRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryIml @Inject constructor(
   private val auth : FirebaseAuth,
   private val firestore : FirebaseFirestore
) : AuthRepository {

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

   override suspend fun registerWithGoogle(credential: AuthCredential) : Flow<Result<String>> = flow{
      val result = auth.signInWithCredential(credential).await()
      val uid = result.user?.uid ?: throw Exception("Uid is null")
      val userDoc = firestore.collection("users").document(uid).get().await()

      if(userDoc.exists()){
         auth.signOut()
         throw Exception("User already exists")
      } else {
        emit(Result.success(uid))
      }

   }.catch {
      emit(Result.failure(Exception(it)))
   }


   override suspend fun signInWithGoogle(credential: AuthCredential) : Flow<Result<Unit>> = flow{
      val result = auth.signInWithCredential(credential).await()
      val uid = result.user?.uid ?: throw Exception("Uid is null")
      val userDoc = firestore.collection("users").document(uid).get().await()

      if(userDoc.exists()){
         emit(Result.success(Unit))
      } else {
         auth.signOut()
         throw Exception("User does not exist")
      }
   }.catch {
      emit(Result.failure(Exception(it)))
   }

}