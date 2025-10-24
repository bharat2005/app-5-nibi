package com.bharat.app5.feature_auth.presentation.register.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bharat.app5.feature_auth.presentation.components.RegistrationStepHolder
import com.bharat.app5.feature_auth.presentation.register.RegisterUiState
import com.bharat.app5.feature_auth.presentation.register.RegisterViewModel
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.commandiron.wheel_picker_compose.WheelDateTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import java.time.LocalDate
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DobStep(modifier: Modifier = Modifier, viewModel: RegisterViewModel, uiState : RegisterUiState) {

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        RegistrationStepHolder(
            modifier = Modifier.align(Alignment.TopCenter),
            primaryText = "Enter your DOB please?"
        ) {

            BoxWithConstraints(
                modifier = Modifier.padding(vertical = 28.dp),
                contentAlignment = Alignment.Center
            ){
                WheelDatePicker(
                    size = DpSize(maxWidth - 18.dp , 180.dp),
                    startDate = uiState.userDetails.dob,
                    rowCount = 5,
                    minDate = LocalDate.of(1950, 1,1),
                    maxDate = LocalDate.now(),
                    textStyle = TextStyle(fontSize = 24.sp),
                    textColor = Color.Black,
                    selectorProperties = WheelPickerDefaults.selectorProperties(
                        enabled = true,
                        shape = RoundedCornerShape(0.dp),
                        color = Color(0xFFf1faee).copy(alpha = 0.2f),
                        border = BorderStroke(2.dp, Color(0xFFf1faee))
                    )
                ){ snappedDateTime -> viewModel.onDobChanged(snappedDateTime)}

            }


        }

        Button(
            onClick = { viewModel.goToNextStep() },
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
        ) {
            Text("Next")
        }
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun MyPreview(){
    DobStep(viewModel = viewModel(), uiState = RegisterUiState())
}