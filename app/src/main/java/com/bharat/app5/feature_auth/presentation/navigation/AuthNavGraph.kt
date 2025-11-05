package com.bharat.app5.feature_auth.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bharat.app5.app_root.AppRoutes
import com.bharat.app5.feature_auth.presentation.register.RegisterScreen
import com.bharat.app5.feature_auth.presentation.start.StartScreen
import com.bharat.app5.feature_legal.presentation.legal.LegalType
import com.bharat.app5.feature_legal.presentation.navigation.LegalScreenRoutes


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.authNavGraph(navController: NavController){
    navigation(
        route = AppRoutes.AuthRoute,
        startDestination = AuthScreenRoutes.Start
    ){



        //Start Screen
        composable(
            route = AuthScreenRoutes.Start,
            enterTransition = null,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(500, easing = FastOutSlowInEasing)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(500, easing = FastOutSlowInEasing)
                )
            },
            popExitTransition = null

        ){
            StartScreen(
                onRegisterClick = {navController.navigate(AuthScreenRoutes.Register)}
            )
        }









        composable(
            AuthScreenRoutes.Register,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(500, easing = FastOutSlowInEasing)
                )
            },
            exitTransition = {
                scaleOut(
                    targetScale = 1.1f,
                    animationSpec = tween(300, easing = FastOutSlowInEasing)
                ) + fadeOut( tween(300, easing = FastOutSlowInEasing))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(500, easing = FastOutSlowInEasing)
                )
            },
            popEnterTransition = {
                scaleIn(
                    initialScale = 1.1f,
                    animationSpec = tween(300, easing = FastOutSlowInEasing)
                ) + fadeIn(tween(300, easing = FastOutSlowInEasing))
            }
            ) { backStackEntry ->
            RegisterScreen(
                onRegisterSuccess = {
                navController.navigate(AppRoutes.MainRoute){
                    popUpTo(AppRoutes.AuthRoute){
                        inclusive = true
                    }
                }
            },
                onExit = {
                    navController.navigateUp()
                },
                onTermsClick = {navController.navigate("${LegalScreenRoutes.LegalScreen}/${LegalType.TERMS_CONDITIONS}")},
                onPrivacyPolicyClick = {navController.navigate("${LegalScreenRoutes.LegalScreen}/${LegalType.PRIVACY_POLICY}")},
                onExternalTransmissionClick = {navController.navigate("${LegalScreenRoutes.LegalScreen}/${LegalType.EXTERNAL_TRANSMISSIONS}")}
        )
        }


    }
}