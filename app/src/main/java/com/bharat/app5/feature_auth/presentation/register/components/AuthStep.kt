package com.bharat.app5.feature_auth.presentation.register.components

import android.app.Activity
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.bharat.app5.R
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bharat.app5.feature_auth.presentation.register.StartViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AuthStep(
    modifier: Modifier = Modifier,
    viewModel : StartViewModel,
    onTermsClick : () -> Unit,
    onPrivacyPolicyClick : () -> Unit,
    onExternalTransmissionClick : () -> Unit
) {
    var isChecked by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false)}
    val context = LocalContext.current

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if(result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try{
                val account = task.getResult(ApiException::class.java)
                val idToken = account?.idToken
                if(idToken != null){
                    viewModel.onLocalGoogleSignInSuccess(idToken)
                } else {
                    viewModel.onLocalGoogleSignInError("Failed to get Google Id Token")
                }

            }catch (e : ApiException){
                viewModel.onLocalGoogleSignInError("Google signIn fialed with status code:${e.statusCode}")

            }
        } else {
            viewModel.onLocalGoogleSignInError("Google SignIn failed or cancelled!")

        }

    }


    val googleSignInOptions = remember {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken("650451806359-uhmqi74fs2eh4ncprnpk2gokdjlcne17.apps.googleusercontent.com")
            .build()
    }

    val googleSignInClient = remember {
        GoogleSignIn.getClient(context, googleSignInOptions)
    }



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
                shape = RoundedCornerShape(8.dp ),
                onClick = {
                    if(isChecked){
                        googleSignInClient.signOut().addOnCompleteListener {
                            googleSignInLauncher.launch(googleSignInClient.signInIntent)
                        }
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
