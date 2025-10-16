package com.bharat.app5.feature_auth.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bharat.app5.core.navigation.Screen
import com.bharat.app5.feature_auth.presentation.login.StartScreen

object AuthScreenRoutes {
    const val Start = "start"
    const val Login = "login"
    const val Register = "register"

}

fun NavGraphBuilder.authGraph(){
    navigation(
        startDestination = AuthScreenRoutes.Start,
        route = Screen.AuthGraph
    ){

        composable(AuthScreenRoutes.Start) {
            StartScreen()
        }


    }
}