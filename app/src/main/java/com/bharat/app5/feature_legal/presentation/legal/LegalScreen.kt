package com.bharat.app5.feature_legal.presentation.legal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


enum class LegalType {
    PRIVACY_POLICY,
    TERMS_CONDITIONS,
    EXTERNAL_TRANSMISSIONS
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LegalScreen(
    type : LegalType,
    onBackArrowPress : () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App5") },
                navigationIcon = {
                    IconButton(onClick = onBackArrowPress) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }

            )
        }

    ) {
        paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {

            Text(type.name)



        }
    }

}