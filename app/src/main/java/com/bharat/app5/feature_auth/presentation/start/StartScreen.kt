package com.bharat.app5.feature_auth.presentation.start

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StartScreen(
    onRegisterClick : () -> Unit,
    onLoginClick : () -> Unit
) {
    Scaffold {
            paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Button(
                onClick = onRegisterClick
            ) {
                Text("Register")
            }

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = onLoginClick
            ) {
                Text("Login")
            }



        }

    }

}