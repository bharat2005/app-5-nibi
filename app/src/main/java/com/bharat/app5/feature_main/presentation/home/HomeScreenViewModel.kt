package com.bharat.app5.feature_main.presentation.home

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val firebaseAuth : FirebaseAuth
) : ViewModel() {
    fun logout(){
        firebaseAuth.signOut()
    }
}