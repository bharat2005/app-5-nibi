package com.bharat.app5.feature_auth.presentation.register.components

import android.R
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bharat.app5.feature_auth.presentation.components.RegistrationStepHolder
import com.bharat.app5.feature_auth.presentation.register.RegisterUiState
import com.bharat.app5.feature_auth.presentation.register.RegisterViewModel
import com.bharat.app5.feature_auth.presentation.register.RegistrationStep

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NameStep(modifier: Modifier = Modifier, viewModel: RegisterViewModel, uiState : RegisterUiState) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }


    Box(
        modifier = Modifier.fillMaxSize().imePadding()
    ){
        RegistrationStepHolder(
            primaryText = "Enter you name please?",
            modifier = Modifier.align(alignment = Alignment.TopCenter)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                contentAlignment = Alignment.Center
            ){
                TextField(
                    singleLine = true,
                    value = uiState.userDetails.name,
                    onValueChange = {
                        if(it.length <= 15) {
                            viewModel.onNameChanged(it)
                        }},
                    modifier = Modifier.focusRequester(focusRequester).padding(0.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    ),
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp
                    )
                    )

            }
        }


        Button(
            enabled = uiState.userDetails.name.isNotEmpty(),
            modifier = Modifier.fillMaxWidth().align(alignment = Alignment.BottomCenter),
            onClick = {viewModel.goToNextStep()}
        ) {
            Text("Next")
        }




    }


}