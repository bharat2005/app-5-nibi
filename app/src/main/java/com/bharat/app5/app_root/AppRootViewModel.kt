package com.bharat.app5.app_root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


enum class AuthState {
    AUTHENTICATED, UNAUTHENTICATED, UNKNOWN
}

@HiltViewModel
class AppRootViewModel @Inject constructor(
    private val firebaseAuth : FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val authOperationState: AuthOperationState
): ViewModel(){

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    val firebaseFlow = callbackFlow {
        val  listner = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser)
        }
        firebaseAuth.addAuthStateListener(listner)
        awaitClose {
            firebaseAuth.removeAuthStateListener(listner)
        }
    }

    val authState : StateFlow<AuthState> = combine(
        firebaseFlow,
        authOperationState.isOperationInProgress
    ){ firebaseUser, isOperationInProgress ->
        Pair(firebaseUser, isOperationInProgress)
    }.filter { (_, isOperationInProgress) ->
        !authOperationState.isOperationInProgress.value
    }.flatMapLatest {  (firebaseUser, _) ->
        if(firebaseUser != null){
          val userDoc = firebaseFirestore.collection("users").document(firebaseUser.uid).get().await()
            if(userDoc.exists()){
                flowOf(firebaseUser)
            } else{
                flowOf(null)
            }
        } else {
            flowOf(null)
        }
    }.map {
        if(it != null) AuthState.AUTHENTICATED else AuthState.UNAUTHENTICATED
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = AuthState.UNKNOWN
    )

    init{
        viewModelScope.launch {
            authState.first{it != AuthState.UNKNOWN}
            _isLoading.value = false
        }
    }


}