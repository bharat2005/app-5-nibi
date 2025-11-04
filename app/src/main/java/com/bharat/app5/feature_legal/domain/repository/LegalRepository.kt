package com.bharat.app5.feature_legal.domain.repository

import com.bharat.app5.feature_legal.domain.model.LegalDocument
import kotlinx.coroutines.flow.Flow

interface LegalRepository {
    fun getLegalDocument(documentType : String) : Flow<Result<LegalDocument>>
}