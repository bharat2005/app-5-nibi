package com.bharat.app5.feature_auth.presentation.register.components

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bharat.app5.feature_auth.domain.model.Gender
import com.bharat.app5.feature_auth.presentation.components.RegistrationStepHolder
import com.bharat.app5.feature_auth.presentation.register.RegisterUiState
import com.bharat.app5.feature_auth.presentation.register.RegisterViewModel
import com.bharat.app5.feature_auth.presentation.register.RegistrationStep

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GenderStep(modifier: Modifier = Modifier, viewModel: RegisterViewModel, uiState : RegisterUiState) {

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        RegistrationStepHolder(
            primaryText = "What is your gender, plase select below?",
            secondaryText = "Please be careful to select the correct gender, as it matter alot?",
            stepIconRes = 12,
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 44.dp).padding(top = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Gender.entries.forEach { gender ->
                    val isSelected = uiState.userDetails.gender == gender
                    val backgroundColor =
                        if (isSelected) MaterialTheme.colorScheme.surface else Color.White
                    val borderColor =
                        if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray

                    Column(
                        modifier = Modifier
                            .size(120.dp)
                            .border(
                                color = borderColor,
                                width = 1.dp,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clip(RoundedCornerShape(12.dp))
                            .clickable(onClick = { viewModel.onGenderSelected(gender) })
                            .background(backgroundColor),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(modifier = Modifier.size(18.dp).background(Color.Black))
                        Text(gender.disPlayName)
                    }


                }
            }
        }

        Button(
            enabled = uiState.userDetails.gender != null,
            modifier =  Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            onClick = {viewModel.goToNextStep()}
        ) {
            Text("Next")
        }
    }
}
