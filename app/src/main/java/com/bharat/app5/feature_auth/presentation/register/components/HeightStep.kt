package com.bharat.app5.feature_auth.presentation.register.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Box(
        modifier = Modifier.fillMaxSize().imePadding()
    ){
        RegistrationStepHolder(
            modifier = Modifier.fillMaxWidth().align(alignment = Alignment.TopCenter),
            primaryText = "Enter your height please?"
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                TextField(
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.focusRequester(focusRequester).width(100.dp).padding(horizontal = 4.dp),
                    value = if(uiState.userDetails.height == null) "" else uiState.userDetails.height.toString(),
                    placeholder = {Text("0.0")},
                    onValueChange = {
                        it.toDoubleOrNull()?.let {
                            val heightText = it
                            viewModel.onHeightChanged(heightText)
                        }
                        },
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    colors = TextFieldDefaults.colors(focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent)
                )
                Text("cm")
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            onClick = {viewModel.goToNextStep() }
        ) {
            Text("Next")
        }
    }



}