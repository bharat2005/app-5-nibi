package com.bharat.app5.app_root

import android.app.Activity
import android.os.Build
import androidx.activity.compose.LocalActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bharat.app5.feature_auth.presentation.navigation.authNavGraph
import com.bharat.app5.feature_legal.presentation.navigation.legalNavGraph
import com.bharat.app5.feature_main.presentation.navigation.mainNavGraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppRoot(
    viewModel: AppRootViewModel
) {
    val navController = rememberNavController()
    val authState by viewModel.authState.collectAsStateWithLifecycle()

    val startDestination = when(authState){
        AuthState.UNKNOWN -> AppRoutes.SplashRoute
        AuthState.UNAUTHENTICATED -> AppRoutes.AuthRoute
        AuthState.AUTHENTICATED -> AppRoutes.MainRoute
    }

    val window = (LocalActivity.current as Activity).window

    SideEffect {
        window.navigationBarColor = Color.Red.toArgb()
    }



    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

            NavHost(
                navController = navController,
                startDestination = startDestination
            ){
                composable(AppRoutes.SplashRoute) { Box(modifier = Modifier.fillMaxSize().background(Color.Transparent), contentAlignment = Alignment.Center){} }
                authNavGraph(navController)
                legalNavGraph(navController)
                mainNavGraph(navController)
            }


    }

}