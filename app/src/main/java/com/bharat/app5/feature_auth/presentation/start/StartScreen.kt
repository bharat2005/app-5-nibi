package com.bharat.app5.feature_auth.presentation.start

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bharat.app5.feature_auth.presentation.register.components.AuthButton
import com.bharat.app5.R


@Composable
fun StartScreen(
    onRegisterClick : () -> Unit,
) {
    Scaffold {
            paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 56.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Button(
                modifier = Modifier.fillMaxWidth().height(50.dp),
                onClick = onRegisterClick
            ) {
                Text("Register")
            }

            Spacer(modifier = Modifier.height(28.dp))

            AuthButton(
                text = "Login with Google",
                onClick = {},
                shape = RoundedCornerShape(24.dp),
                borderColor = Color.Gray,
                backgroundColor = Color.White,
                contentColor = Color.Black,
                iconRes = R.drawable.google_icon_button,
                modifier = Modifier.fillMaxWidth()

            )



        }

    }

}