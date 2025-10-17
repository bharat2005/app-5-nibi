package com.bharat.app5.feature_auth.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bharat.app5.core.navigation.Screen
import com.bharat.app5.feature_auth.presentation.login.LoginScreen
import com.bharat.app5.feature_auth.presentation.register.RegisterScreen
import com.bharat.app5.feature_auth.presentation.start.StartScreen

object AuthScreenRoutes {
    const val Start = "start"
    const val Login = "login"
    const val Register = "register"

}

fun NavGraphBuilder.authGraph(navController: NavController){
    navigation(
        startDestination = AuthScreenRoutes.Start,
        route = Screen.AuthGraph
    ){

        composable(AuthScreenRoutes.Start) {
            StartScreen(
                onLoginClick = {
                    navController.navigate(AuthScreenRoutes.Login)
                },
                onRegisterClick = {
                    navController.navigate(AuthScreenRoutes.Register)
                }
            )
        }

        composable(AuthScreenRoutes.Login) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.HomeGraph){
                        popUpTo(Screen.AuthGraph){inclusive = true}
                    }
                }
            )
        }

        composable(AuthScreenRoutes.Register) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Screen.HomeGraph){
                        popUpTo(Screen.AuthGraph){inclusive = true}
                    }
                }
            )
        }


    }
}