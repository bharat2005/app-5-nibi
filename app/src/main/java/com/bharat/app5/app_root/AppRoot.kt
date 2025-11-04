package com.bharat.app5.app_root

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
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
    val isAuthenticated by viewModel.isAuthenticated.collectAsState()
    var startDestination = if(isAuthenticated) AppRoutes.MainRoute else AppRoutes.AuthRoot

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        NavHost(
            navController = navController,
            startDestination = startDestination
        ){
            authNavGraph(navController)
            legalNavGraph(navController)
            mainNavGraph(navController)
        }

    }


}