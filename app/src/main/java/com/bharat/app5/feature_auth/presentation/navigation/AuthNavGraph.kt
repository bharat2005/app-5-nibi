package com.bharat.app5.feature_auth.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bharat.app5.core.navigation.ScreenRoutes
import com.bharat.app5.feature_auth.domain.usecase.RegisterUserUseCase
import com.bharat.app5.feature_auth.presentation.register.RegisterScreen
import com.bharat.app5.feature_auth.presentation.register.RegisterViewModel
import com.bharat.app5.feature_auth.presentation.start.StartScreen
import com.bharat.app5.feature_legal.presentation.legal.LegalType
import com.bharat.app5.feature_legal.presentation.navigation.LegalScreenRoutes


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.authNavGraph(navController: NavController){
    navigation(
        route = ScreenRoutes.AuthScreenRoute,
        startDestination = AuthScreenRoutes.Start
    ){

        composable(
            AuthScreenRoutes.Start,
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
        ) {
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
                slideOutHorizontally(targetOffsetX = { it },
                    animationSpec = tween(500, easing = FastOutSlowInEasing)
                )
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it },
                    animationSpec = tween(500, easing = FastOutSlowInEasing)
                )
            },
            popEnterTransition = null

            ) { backStackEntry ->
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
                },
                onTermsClick = {navController.navigate("${LegalScreenRoutes.LegalScreen}/${LegalType.TERMS_CONDITIONS}")},
                onPrivacyPolicyClick = {navController.navigate("${LegalScreenRoutes.LegalScreen}/${LegalType.PRIVACY_POLICY}")},
                onExternalTransmissionClick = {navController.navigate("${LegalScreenRoutes.LegalScreen}/${LegalType.EXTERNAL_TRANSMISSIONS}")}
        ) }


    }
}