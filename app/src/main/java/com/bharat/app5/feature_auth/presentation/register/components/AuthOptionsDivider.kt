package com.bharat.app5.feature_auth.presentation.register.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthOptionsDivider(
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(modifier = Modifier.weight(1f))
        Text("or", color = Color.Gray, modifier = Modifier.padding(horizontal = 12.dp))
        Divider(modifier = Modifier.weight(1f))
    }
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        "More Options Coming Soon",
        color = Color.Gray,
        fontSize = 16.sp,
        textAlign = TextAlign.Center
    )

}