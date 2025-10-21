package com.bharat.app5.feature_auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun RegistrationStepHolder(
    modifier: Modifier = Modifier,
    stepIconRes : Int? = null,
    primaryText: String,
    secondaryText: String? = null,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(horizontal = 44.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically)
    ) {
        Box(
            modifier = Modifier.size(40.dp).background(Color.Black)
        )

        Text(
            primaryText,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 12.dp ),
            style = MaterialTheme.typography.titleLarge
        )

        if(secondaryText != null){
            Text(
                secondaryText,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        content()


    }

}