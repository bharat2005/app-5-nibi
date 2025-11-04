package com.bharat.app5.feature_legal.presentation.legal

import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharat.app5.feature_legal.domain.model.LegalDocument
import com.bharat.app5.feature_legal.domain.usecase.GetLegalDocumentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class LegalType {
    PRIVACY_POLICY,
    TERMS_CONDITIONS,
    EXTERNAL_TRANSMISSIONS
}
data class LegalScreenState(
    val title : String? = null,
    val isLoading : Boolean = true,
    val legalDocument : LegalDocument? = null,
    val error : String? = null
)

@HiltViewModel
class LegalViewModel @Inject constructor(
    private val getLegalDocumentUseCase: GetLegalDocumentUseCase,
    stateHandle: SavedStateHandle
) : ViewModel() {

    private val _legalUiState = MutableStateFlow(LegalScreenState())
    val legalUiState = _legalUiState.asStateFlow()

    init {
        val documentType : String? = stateHandle["documentType"]

        val title = when(documentType){
            "PRIVACY_POLICY" -> "Privacy Policy"
            "TERMS_CONDITIONS" -> "Terms & Conditions"
            "EXTERNAL_TRANSMISSIONS" -> "External Transmissions"
            else -> null
        }

        _legalUiState.update { it.copy(title = title) }

        if(documentType  != null){
            fetchLegalData(documentType)
        } else {
            _legalUiState.update {
                it.copy(error = "Document type not found", isLoading = false)
            }

        }
    }

   private fun fetchLegalData(documentType : String){
       viewModelScope.launch {
           getLegalDocumentUseCase(documentType)
               .collect{ result ->
                   result.fold(
                       onSuccess = { document ->
                           _legalUiState.update { it.copy(isLoading = false, error = null, legalDocument = document) }

                       },
                       onFailure = { e ->
                           _legalUiState.update { it.copy(isLoading = false, error = e.localizedMessage, legalDocument = null) }
                       }
                   )
               }
            }
        }




}