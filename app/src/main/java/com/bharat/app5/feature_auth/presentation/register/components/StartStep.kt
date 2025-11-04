package com.bharat.app5.feature_auth.presentation.register.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bharat.app5.R
import com.bharat.app5.feature_auth.presentation.register.StartViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StartStep(
   viewModel: StartViewModel,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                viewModel.goToNextStep()
            }
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