package com.bharat.app5.feature_legal.data.repository

import com.bharat.app5.feature_legal.domain.model.LegalDocument
import com.bharat.app5.feature_legal.domain.repository.LegalRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LegalRepositoryIml @Inject constructor(
    private val firestore : FirebaseFirestore
) : LegalRepository {
    override fun getLegalDocument(documentType: String): Flow<Result<LegalDocument>> = flow {
        val docSnapShot = firestore.collection("legal").document(documentType).get().await()
        val legalDocument = docSnapShot.toObject(LegalDocument::class.java)
        if(legalDocument != null){
            emit(Result.success(legalDocument))
        } else {
            emit(Result.failure(Exception("Document doesnt exist!!")))
        }
    }.catch { e->
        emit(Result.failure(Exception(e.localizedMessage)))
    }
}