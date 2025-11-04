package com.bharat.app5.feature_legal.presentation.legal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LegalScreen(
    onBackArrowPress : () -> Unit,
    viewModel: LegalViewModel = hiltViewModel()
) {
    val legalUiState by viewModel.legalUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(legalUiState.title ?: "") },
                navigationIcon = {
                    IconButton(onClick = onBackArrowPress) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
            ))
        }

    ) {
        paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            if(legalUiState.isLoading){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if(legalUiState.error != null){
                AlertDialog(
                    onDismissRequest = {},
                    title = { Text("Error") },
                    text = { Text(legalUiState.error ?: "") },
                    confirmButton = {
                        Button(onClick = {onBackArrowPress()}, modifier =  Modifier.fillMaxWidth()) {
                         Text("OK")
                        }
                    }
                )
            } else if(legalUiState.legalDocument != null){
                Text(legalUiState.legalDocument?.content ?: "")
            }

        }

    }

}