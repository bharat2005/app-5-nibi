package com.bharat.app5.feature_auth.presentation.register.components

import android.view.RoundedCorner
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.bharat.app5.R
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

@Composable
fun AuthStep(
    modifier: Modifier = Modifier,
    onGoogleRegisterClick : () -> Unit,
    onTermsClick : () -> Unit,
    onPrivacyPolicyClick : () -> Unit,
    onExternalTransmissionClick : () -> Unit
) {
    var isChecked by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false)}

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //Title Text
        Text(
            "Create an account and start diagnosing your personilised plan",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
            )

            // CheckBox Row
        AuthCheckBoxRow(
            isChecked = isChecked,
            onCheckedChanged = {
                isChecked = it
                if(it) showError = false
            },
            onTermsClick  = onTermsClick,
            onPrivacyPolicyClick = onPrivacyPolicyClick,
            onExternalTransmissionClick = onExternalTransmissionClick
        )


        // Error message
        AuthErrorMessage(
            showError = showError
        )


        //Auth Buttons
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthButton(
                modifier = Modifier.fillMaxWidth(0.9f),
                borderColor = Color.LightGray,
                backgroundColor = Color.White,
                contentColor = Color.Black,
                iconRes = R.drawable.google_icon_button,
                text = "Register with Google",
                onClick = {
                    if(isChecked){
                        onGoogleRegisterClick()
                    } else {
                        showError = true
                    }

                }
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        AuthOptionsDivider(modifier = Modifier.fillMaxWidth())












    }

}
