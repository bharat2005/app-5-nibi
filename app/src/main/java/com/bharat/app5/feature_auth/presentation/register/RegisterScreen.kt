package com.bharat.app5.feature_auth.presentation.register

import android.app.Activity

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.ModifierLocalReadScope
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
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.firestore.bundle.BundleReader
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_components_ViewModelComponent
import kotlinx.coroutines.launch
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharat.app5.feature_auth.presentation.register.components.RegisterHeader


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterScreen(
onRegisterSuccess : () -> Unit,
onExit : () -> Unit,
onTermsClick : () -> Unit,
onPrivacyPolicyClick : () -> Unit,
onExternalTransmissionClick : () -> Unit,
viewModel : RegisterViewModel = hiltViewModel()
) {



    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current


    LaunchedEffect(uiState.registrationSuccess) {
        if(uiState.registrationSuccess){
            Toast.makeText(context, "Registraion SuccessFFull!!", Toast.LENGTH_LONG).show()
        }
    }

    fun handleBack() : Unit {
        val currentStep = uiState.currentStep
        if(currentStep == RegistrationStep.GENDER_STEP){
            onExit()
        } else {
            viewModel.goToPreviousStep()
        }
    }

    BackHandler(onBack = {handleBack()})



            Column(
                modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.systemBars)
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
                            modifier = Modifier.fillMaxSize().padding(horizontal = 38.dp, vertical = 24.dp).windowInsetsPadding(WindowInsets.ime)
                        ) {
                        when (targetState) {
                            RegistrationStep.GENDER_STEP -> GenderStep(viewModel = viewModel, uiState = uiState)

                            RegistrationStep.GOAL_STEP -> GoalStep(viewModel = viewModel,  uiState = uiState)

                            RegistrationStep.NAME_STEP -> NameStep(viewModel = viewModel, uiState = uiState)

                            RegistrationStep.DOB_STEP -> DobStep(viewModel = viewModel, uiState = uiState)

                            RegistrationStep.HEIGHT_STEP -> HeightStep(viewModel = viewModel, uiState = uiState)

                            RegistrationStep.WEIGHT_STEP -> WeightStep(viewModel = viewModel, uiState = uiState)

                            RegistrationStep.AUTH_STEP -> AuthStep(
                                viewModel = viewModel,
                                onTermsClick = onTermsClick,
                                onPrivacyPolicyClick = onPrivacyPolicyClick,
                                onExternalTransmissionClick = onExternalTransmissionClick
                            )

                        }
                    }
                }

            }


            //Error Dialog
        if(uiState.registrationError != null){
            AlertDialog(
                title = {Text("Notice")},
                text = {Text(uiState.registrationError!!)},
                onDismissRequest = {viewModel.onRegistrationErrorDismiss()},
                confirmButton = {
                    TextButton(
                        onClick = {viewModel.onRegistrationErrorDismiss()},
                    ) { Text("OK")}
                }
            )
        }

            if(uiState.isRegistering){
                Dialog (
                    onDismissRequest = {}
                ){
                    CircularProgressIndicator()
                }

    }
}