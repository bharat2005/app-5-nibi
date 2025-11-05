package com.bharat.app5.feature_auth.presentation.register

import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bharat.app5.feature_auth.presentation.register.components.AuthStep
import com.bharat.app5.feature_auth.presentation.register.components.DobStep
import com.bharat.app5.feature_auth.presentation.register.components.GenderStep
import com.bharat.app5.feature_auth.presentation.register.components.GoalStep
import com.bharat.app5.feature_auth.presentation.register.components.HeightStep
import com.bharat.app5.feature_auth.presentation.register.components.NameStep
import com.bharat.app5.feature_auth.presentation.register.components.WeightStep
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharat.app5.feature_auth.presentation.register.components.RegisterHeader


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onExit: () -> Unit,
    onTermsClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    onExternalTransmissionClick: () -> Unit,
    viewModel: StartViewModel = hiltViewModel()
) {


    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current


    LaunchedEffect(uiState.registrationSuccess) {
        if (uiState.registrationSuccess) {
            Toast.makeText(context, "Registraion SuccessFFull!!", Toast.LENGTH_LONG).show()
        }
    }

    fun handleBack(): Unit {
        val currentStep = uiState.currentStep
        if (currentStep == RegistrationStep.GENDER_STEP) {
            onExit()
        } else {
            viewModel.goToPreviousStep()
        }
    }

    BackHandler(onBack = { handleBack() })


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars)
        ) {

            //Register Header
            RegisterHeader(uiState, onExit, handleBack = { handleBack() })


            //step content
            AnimatedContent(
                targetState = uiState.currentStep,
                transitionSpec = {
                    if (targetState.ordinal > initialState.ordinal) {
                        slideInHorizontally(initialOffsetX = { it }) + fadeIn() togetherWith
                                slideOutHorizontally(targetOffsetX = { -it }) + fadeOut()
                    } else {
                        slideInHorizontally(initialOffsetX = { -it }) + fadeIn() togetherWith
                                slideOutHorizontally(targetOffsetX = { it }) + fadeOut()
                    }
                }
            ) { targetState ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 38.dp, vertical = 24.dp)
                        .windowInsetsPadding(WindowInsets.ime)
                ) {
                    when (targetState) {

                        RegistrationStep.GENDER_STEP -> GenderStep(
                            viewModel = viewModel,
                            uiState = uiState
                        )

                        RegistrationStep.GOAL_STEP -> GoalStep(
                            viewModel = viewModel,
                            uiState = uiState
                        )

                        RegistrationStep.NAME_STEP -> NameStep(
                            viewModel = viewModel,
                            uiState = uiState
                        )

                        RegistrationStep.DOB_STEP -> DobStep(
                            viewModel = viewModel,
                            uiState = uiState
                        )

                        RegistrationStep.HEIGHT_STEP -> HeightStep(
                            viewModel = viewModel,
                            uiState = uiState
                        )

                        RegistrationStep.WEIGHT_STEP -> WeightStep(
                            viewModel = viewModel,
                            uiState = uiState
                        )

                        RegistrationStep.AUTH_STEP -> AuthStep(
                            viewModel = viewModel,
                            onTermsClick = onTermsClick,
                            onPrivacyPolicyClick = onPrivacyPolicyClick,
                            onExternalTransmissionClick = onExternalTransmissionClick
                        )

                    }
                }
            }


            //Error Dialog
            if (uiState.registrationError != null) {
                AlertDialog(
                    title = { Text("Error") },
                    text = { Text(uiState.registrationError!!) },
                    onDismissRequest = { viewModel.onLocalGoogleSignInErrorDismiss() },
                    confirmButton = {
                        TextButton(
                            onClick = { viewModel.onLocalGoogleSignInErrorDismiss() },
                        ) { Text("OK") }
                    }
                )
            }


        }



        if (uiState.isRegistering) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.5f))
                    .pointerInput(Unit) {},
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        }


    }


}