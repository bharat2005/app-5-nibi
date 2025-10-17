package com.bharat.app5.feature_main.presentaiton.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bharat.app5.core.navigation.Screen
import com.bharat.app5.feature_main.presentaiton.home.HomeScreen

object MainScreenRoutes {
    const val Home = "home"
}

fun NavGraphBuilder.homeGraph(navController: NavController){
    navigation(
        startDestination = MainScreenRoutes.Home,
        route = Screen.HomeGraph
    ){
        composable(MainScreenRoutes.Home) {
            HomeScreen()
        }
    }
}