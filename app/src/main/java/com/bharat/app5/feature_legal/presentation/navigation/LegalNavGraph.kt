package com.bharat.app5.feature_legal.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.bharat.app5.core.navigation.ScreenRoutes
import com.bharat.app5.feature_legal.presentation.legal.LegalScreen
import com.bharat.app5.feature_legal.presentation.legal.LegalType

fun NavGraphBuilder.legalNavGraph(navController: NavController) {
    navigation(
        startDestination = LegalScreenRoutes.LegalScreen,
        route = ScreenRoutes.LegalScreenRoute
    ){
        composable(
            route = "${LegalScreenRoutes.LegalScreen}/{type}",
            arguments = listOf(
                navArgument("type"){ type = NavType.StringType }
            )
        ) { backStackEntry ->
            val typeArg = backStackEntry.arguments?.getString("type")
            val type = LegalType.valueOf(typeArg!!)
            LegalScreen(
                type = type,
                onBackArrowPress = {navController.navigateUp()}
            )

        }
    }

}