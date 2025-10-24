package com.bharat.app5.feature_auth.presentation.register.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
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
            Box(
                modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                contentAlignment = Alignment.Center
            ){
                TextField(
                    singleLine = true,
                    modifier = Modifier.focusRequester(focusRequester).width(100.dp),
                    value = uiState.userDetails.height.toString(),
                    onValueChange = {viewModel.onHeightChanged(it.toDouble())}
                )

            }

        }
        Button(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            onClick = {viewModel.goToNextStep()}
        ) {
            Text("Next")
        }
    }



}