package com.bharat.app5.app_root

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AppRootViewModel @Inject constructor(
    private val auth : FirebaseAuth
): ViewModel(){
    val isAuthenticated = MutableStateFlow(auth.currentUser != null)

    init {
        auth.addAuthStateListener {
            isAuthenticated.value = it.currentUser != null
        }
    }


}