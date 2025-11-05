package com.bharat.app5.app_root

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.bharat.app5.feature_auth.presentation.navigation.authNavGraph
import com.bharat.app5.feature_legal.presentation.navigation.legalNavGraph
import com.bharat.app5.feature_main.presentation.navigation.mainNavGraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppRoot(
    viewModel: AppRootViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val authState by viewModel.authState.collectAsStateWithLifecycle()

    LaunchedEffect(navController, authState) {
        when(authState){
            AuthState.AUTHENTICATED -> {
                navController.navigate(AppRoutes.MainRoute){
                    popUpTo(AppRoutes.AuthRoot){inclusive = true}
                }
            }
            AuthState.UNAUTHENTICATED -> {
                navController.navigate(AppRoutes.AuthRoot){
                    popUpTo(AppRoutes.AuthRoot){ inclusive = true }
                }
            }
            AuthState.UNKNOWN -> {}
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        NavHost(
            navController = navController,
            startDestination = AppRoutes.AuthRoot
        ){
            authNavGraph(navController)
            legalNavGraph(navController)
            mainNavGraph(navController)
        }

    }

    if(AuthState.UNKNOWN == authState){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }

    }



}