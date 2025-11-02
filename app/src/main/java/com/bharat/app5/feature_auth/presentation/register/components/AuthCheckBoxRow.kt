package com.bharat.app5.feature_auth.presentation.register.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthCheckBoxRow(
    isChecked : Boolean,
    onCheckedChanged : (Boolean) -> Unit,
    onTermsClick : () -> Unit,
    onPrivacyPolicyClick : () -> Unit,
    onExternalTransmissionClick : () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChanged
        )

        TextWithLinks(
            onTermsClick = onTermsClick,
            onPrivacyPolicyClick = onPrivacyPolicyClick,
            onExternalTransmissionClick = onExternalTransmissionClick
        )
    }

}