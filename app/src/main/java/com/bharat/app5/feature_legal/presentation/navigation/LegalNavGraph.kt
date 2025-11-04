package com.bharat.app5.feature_legal.presentation.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.bharat.app5.app_root.AppRoutes
import com.bharat.app5.feature_legal.presentation.legal.LegalScreen

fun NavGraphBuilder.legalNavGraph(navController: NavController) {
    navigation(
        startDestination = LegalScreenRoutes.LegalScreen,
        route = AppRoutes.LegalRoute
    ) {
        composable(
            route = "${LegalScreenRoutes.LegalScreen}/{documentType}",
            enterTransition = {
                scaleIn(
                    initialScale = 0.9f,
                    animationSpec = tween(300, easing = FastOutSlowInEasing),
                ) + fadeIn( tween(300, easing = FastOutSlowInEasing))
            },
            popEnterTransition = null,
            exitTransition = null,
            popExitTransition = {
                scaleOut(
                    targetScale = 0.9f,
                    animationSpec = tween(300, easing = FastOutSlowInEasing),
                ) + fadeOut( tween(300, easing = FastOutSlowInEasing))
            },
            arguments = listOf(
                navArgument("documentType") { type = NavType.StringType }
            )
        ) {
            LegalScreen(
                onBackArrowPress = { navController.navigateUp() },
            )

        }
    }

}