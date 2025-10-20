package com.bharat.app5.feature_main.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bharat.app5.core.navigation.ScreenRoutes
import com.bharat.app5.feature_main.presentation.home.HomeScreen

fun NavGraphBuilder.mainNavGraph(navController: NavController){
    navigation(
        startDestination = MainScreenRoutes.Home,
        route = ScreenRoutes.MainScreenRoute
    ){
        composable(MainScreenRoutes.Home) {
            HomeScreen()
        }

    }

}