package com.bharat.app5.feature_auth.presentation.start

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.bharat.app5.feature_auth.presentation.register.components.AuthButton
import com.bharat.app5.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlin.contracts.contract

@Composable
fun StartScreen(
    onRegisterClick : () -> Unit,
    viewModel: StartViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    val googleSignInOptions = remember {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken("650451806359-uhmqi74fs2eh4ncprnpk2gokdjlcne17.apps.googleusercontent.com")
            .build()
    }

    val googleSignInClient = remember {
        GoogleSignIn.getClient(context, googleSignInOptions)
    }

    val googleSingInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if(result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                val idToken = account?.idToken ?: throw Exception("Id Token is null")
                viewModel.onLocalGoogleSignInSuccess(idToken)
            } catch(e: Exception){
                viewModel.onLocalGoogleSignInError(e.message ?: "Something went wrong")
            }
        } else {
            viewModel.onLocalGoogleSignInError("Google SignIn failed or cancelled!")

        }

    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
        ){
            Button(
                onClick = onRegisterClick,
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Register")
            }

            AuthButton(
                text = "Login with Google",
                onClick = {
                    googleSingInLauncher.launch(googleSignInClient.signInIntent)
                },
                shape = RoundedCornerShape(24.dp),
                contentColor = Color.Black,
                iconRes = R.drawable.google_icon_button,
                backgroundColor = Color.White,
                borderColor = Color.LightGray,
                modifier = Modifier.fillMaxWidth().height(50.dp)
            )
        }


        if(uiState.loginError != null){
            AlertDialog(
                title = {Text("Error")},
                text = {Text(uiState.loginError!!)},
                onDismissRequest = {
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.onLocalGoogleSignInErrorDismiss()
                        },
                    ) {
                        Text("OK")
                    }
                }
            )
        }

        if(uiState.isLoggingIn){
            Dialog(
                onDismissRequest = {}
            ) {
                CircularProgressIndicator()
            }
        }






    }

}