package com.bharat.app5.app_root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


enum class AuthState {
    AUTHENTICATED, UNAUTHENTICATED, UNKNOWN
}

@HiltViewModel
class AppRootViewModel @Inject constructor(
    private val firebaseAuth : FirebaseAuth
): ViewModel(){

    val authState : StateFlow<AuthState> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener{ auth ->
            trySend(auth.currentUser)
        }

        firebaseAuth.addAuthStateListener(listener)
        awaitClose {
            firebaseAuth.removeAuthStateListener(listener)
        }

    }.map {
        if(it != null) AuthState.AUTHENTICATED else AuthState.UNAUTHENTICATED
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = AuthState.UNKNOWN
    )


}