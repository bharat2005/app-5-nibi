package com.bharat.app5.feature_auth.presentation.register.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.bharat.app5.R
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthStep(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            "Create an account and start diagnosing your personilised plan",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
            )

        //CheckBox
        Spacer(modifier = Modifier.height(38.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Image(
                    painterResource(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark),
                    contentDescription = "google Icon"
                )

                Text("Register with Google")

            }

        }
    }

}