package com.bharat.app5.feature_auth.presentation.register.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthErrorMessage(
    showError : Boolean
) {
    AnimatedVisibility(
        visible = showError,
        enter =  expandVertically(),
        exit =  shrinkVertically()
    ){
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Warning, contentDescription = null, tint = Color.Red)
            Text(
                "Please review the above information and check \"I agree\" to proceed with the procedure.",
                color = Color.Red,
                fontSize = 14.sp,
                lineHeight = 14.sp,
                modifier = Modifier.padding(horizontal = 18.dp)
            )

        }
    }


}