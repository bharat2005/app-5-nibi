package com.bharat.app5.feature_auth.presentation.register.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bharat.app5.feature_auth.presentation.components.RegistrationStepHolder
import com.bharat.app5.feature_auth.presentation.register.RegistrationStep

@Composable
fun GenderStep(modifier: Modifier = Modifier) {

    RegistrationStepHolder(
        primaryText = "What is your gender, plase select below?",
        secondaryText = "Please be careful to select the correct gender, as it matter alot?"
    ) {

    }



}