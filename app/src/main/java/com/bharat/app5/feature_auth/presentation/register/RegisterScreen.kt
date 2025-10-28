package com.bharat.app5.feature_auth.presentation.register

import android.os.Build
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.modifier.ModifierLocalReadScope
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bharat.app5.feature_auth.presentation.components.RegistrationStepHolder
import com.bharat.app5.feature_auth.presentation.register.components.DobStep
import com.bharat.app5.feature_auth.presentation.register.components.GenderStep
import com.bharat.app5.feature_auth.presentation.register.components.GoalStep
import com.bharat.app5.feature_auth.presentation.register.components.HeightStep
import com.bharat.app5.feature_auth.presentation.register.components.NameStep
import com.google.firebase.firestore.bundle.BundleReader

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterScreen(
onRegisterSuccess : () -> Unit,
onExit : () -> Unit,
viewModel: RegisterViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    BackHandler {
        val currentStep = uiState.currentStep
        if(currentStep == RegistrationStep.GENDER_STEP){
            onExit()
        } else {
            viewModel.goToPreviousStep()
        }
    }

    Scaffold { paddingValues ->

        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(top = 40.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                //progress bar
                Spacer(modifier = Modifier.height(50.dp))

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
                            modifier = Modifier.fillMaxSize().padding(horizontal = 38.dp)
                                .padding(bottom = 24.dp)
                        ) {
                        when (targetState) {
                            RegistrationStep.GENDER_STEP -> GenderStep(viewModel = viewModel, uiState = uiState)

                            RegistrationStep.GOAL_STEP -> GoalStep(viewModel = viewModel,  uiState = uiState)

                            RegistrationStep.NAME_STEP -> NameStep(viewModel = viewModel, uiState = uiState)

                            RegistrationStep.DOB_STEP -> DobStep(viewModel = viewModel, uiState = uiState)

                            RegistrationStep.HEIGHT_STEP -> HeightStep(viewModel = viewModel, uiState = uiState)

                            RegistrationStep.WEIGHT_STEP -> null

                            RegistrationStep.AUTH_STEP -> null

                        }
                    }
                }

            }

        }
    }
}