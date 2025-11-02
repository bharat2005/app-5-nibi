package com.bharat.app5.feature_auth.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bharat.app5.core.navigation.ScreenRoutes
import com.bharat.app5.feature_auth.presentation.login.LoginScreen
import com.bharat.app5.feature_auth.presentation.register.RegisterScreen
import com.bharat.app5.feature_auth.presentation.start.StartScreen




@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.authNavGraph(navController: NavController){
    navigation(
        route = ScreenRoutes.AuthScreenRoute,
        startDestination = AuthScreenRoutes.Register
    ){
        composable(AuthScreenRoutes.Start) {
            StartScreen(
                onLoginClick = {navController.navigate(AuthScreenRoutes.Login)},
                onRegisterClick = {navController.navigate(AuthScreenRoutes.Register)}
            )
        }

        composable(AuthScreenRoutes.Register) {
            RegisterScreen(
                onRegisterSuccess = {
                navController.navigate(ScreenRoutes.MainScreenRoute){
                    popUpTo(ScreenRoutes.AuthScreenRoute){
                        inclusive = true
                    }
                }
            },
                onExit = {
                    navController.navigateUp()
                }
        ) }

        composable(AuthScreenRoutes.Login) {
            LoginScreen(
            onLoginSuccess = {navController.navigate(ScreenRoutes.MainScreenRoute){
                popUpTo(ScreenRoutes.AuthScreenRoute){
                    inclusive = true
                }
            } }
        ) }

    }
}