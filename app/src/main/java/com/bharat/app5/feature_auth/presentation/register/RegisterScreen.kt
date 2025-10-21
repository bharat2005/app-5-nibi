package com.bharat.app5.feature_auth.presentation.register

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bharat.app5.feature_auth.presentation.register.components.GenderStep

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterScreen(
onRegisterSuccess : () -> Unit,
viewModel: RegisterViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
        ) {
            AnimatedContent(
                targetState = uiState.currentStep,

                transitionSpec = {
                    slideInHorizontally(initialOffsetX = { it }) + fadeIn() togetherWith
                            slideOutHorizontally(targetOffsetX = { -it }) + fadeOut()

                }
            ) { targetState ->
                when (targetState) {
                    RegistrationStep.GENDER_STEP -> GenderStep(viewModel = viewModel)

                    RegistrationStep.GOAL_STEP -> Column {
                        Text("Goal")
                        Button(onClick = { viewModel.goToNextStep() }) {
                            Text("Next")
                        }
                    }

                    RegistrationStep.NAME_STEP -> Column {
                        Text("Name")
                        Button(onClick = { viewModel.goToNextStep() }) {
                            Text("Next")
                        }
                    }

                    RegistrationStep.DOB_STEP -> Column {
                        Text("DOB")
                        Button(onClick = { viewModel.goToNextStep() }) {
                            Text("Next")
                        }
                    }

                    RegistrationStep.HEIGHT_STEP -> Column {
                        Text("Height")
                        Button(onClick = { viewModel.goToNextStep() }) {
                            Text("Next")
                        }
                    }

                    RegistrationStep.WEIGHT_STEP -> Column {
                        Text("Weight")

                    }

                    RegistrationStep.AUTH_STEP -> Column {
                        Text("Auth")
                        Button(onClick = {}) {
                            Text("Register")
                        }
                    }
                }
            }

        }
    }
}