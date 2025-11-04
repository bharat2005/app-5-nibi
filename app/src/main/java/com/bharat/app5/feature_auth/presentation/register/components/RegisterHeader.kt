package com.bharat.app5.feature_auth.presentation.register.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bharat.app5.feature_auth.presentation.register.RegisterUiState
import com.bharat.app5.feature_auth.presentation.register.RegistrationStep


@Composable
fun RegisterHeader(
    uiState : RegisterUiState,
    onExit : () -> Unit,
    handleBack : () -> Unit
) {
    val totalSteps = RegistrationStep.values().size
    val currentStep = uiState.currentStep.ordinal + 1
    val progress = currentStep / totalSteps.toFloat()

    val animtedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(600, easing = LinearOutSlowInEasing)
    )

    Row(
        modifier = Modifier.fillMaxWidth().height(50.dp),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        IconButton(onClick = handleBack) {
            Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Gray)
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){

            LinearProgressIndicator(
                progress = animtedProgress,
                modifier = Modifier.fillMaxWidth(0.8f).height(8.dp).clip(RoundedCornerShape(50.dp)),
                color = MaterialTheme.colorScheme.primary,
                trackColor = Color.LightGray
            )

        }






    }


}