package com.bharat.app5.feature_auth.presentation.register.components

import android.R
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bharat.app5.feature_auth.presentation.components.RegistrationStepHolder
import com.bharat.app5.feature_auth.presentation.register.RegisterUiState
import com.bharat.app5.feature_auth.presentation.register.RegisterViewModel
import com.bharat.app5.feature_auth.presentation.register.RegistrationStep

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NameStep(modifier: Modifier = Modifier, viewModel: RegisterViewModel, uiState : RegisterUiState) {


    Box(
        modifier = Modifier.fillMaxSize()
    ){
        RegistrationStepHolder(
            primaryText = "Enter you name please?",
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                contentAlignment = Alignment.Center
            ){
                TextField(
                    value = uiState.userDetails.name,
                    onValueChange = {viewModel.onNameChanged(it)},
                    modifier = Modifier.padding(0.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center
                    )
                    )

            }
        }


        Button(
            enabled = uiState.userDetails.name != "",
            modifier = Modifier.fillMaxWidth(),
            onClick = {viewModel.goToNextStep()}
        ) {
            Text("Next")
        }




    }


}