package com.bharat.app5.feature_auth.presentation.register.components

import android.os.Build
import android.service.autofill.TextValueSanitizer
import android.view.textclassifier.TextSelection
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

import com.bharat.app5.feature_auth.presentation.components.RegistrationStepHolder
import com.bharat.app5.feature_auth.presentation.register.RegisterUiState
import com.bharat.app5.feature_auth.presentation.register.RegisterViewModel
import com.bharat.app5.feature_auth.presentation.register.RegistrationStep

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HeightStep(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel,
    uiState : RegisterUiState
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    var heightTextValue by remember(uiState.userDetails.height) {
        mutableStateOf(
            TextFieldValue(
                text = uiState.userDetails.height?.toString() ?: "",
                selection = TextRange(uiState.userDetails.height.toString().length)
            )
        )
    }
    var heigthError by remember { mutableStateOf<String?>(null)}

    val heightValue = heightTextValue.text.toDoubleOrNull()
    val isButtonEnabled = heightValue != null



    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        RegistrationStepHolder(
            modifier = Modifier.fillMaxWidth().align(alignment = Alignment.TopCenter),
            primaryText = "Enter your height please?"
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(18.dp, alignment = Alignment.CenterHorizontally)
            ){
                TextField(
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.focusRequester(focusRequester).width(100.dp),
                    value = heightTextValue,
                    onValueChange = { newValue ->
                        val regex = """^\d{0,6}\.?\d{0,1}$""".toRegex()
                        if(newValue.text.matches(regex)){
                            heightTextValue = newValue.copy(
                                selection = TextRange(newValue.text.length)
                            )
                            heigthError = null
                        }
                        },
                    textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 18.sp),
                    colors = TextFieldDefaults.colors(focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent)
                )
                Text("cm")
            }

            AnimatedVisibility(
                visible = heigthError != null,
                enter = fadeIn(animationSpec = tween(600)),
                exit = fadeOut(animationSpec = tween(600))
            ) {
                Text(heigthError ?: "", color = Color.Red)
            }



        }
        Button(
            enabled = isButtonEnabled,
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            onClick = {
                if(heightValue == null) return@Button
                if (heightValue !in 80.0..250.0){
                    heigthError = "Please enter a height between 80.0 cm and 250.0 cm"
                } else {
                    heigthError = null
                    viewModel.onHeightChanged(heightValue)
                    keyboardController?.hide()
                    viewModel.goToNextStep()

                }

            }
        ) {
            Text("Next")
        }
    }

}