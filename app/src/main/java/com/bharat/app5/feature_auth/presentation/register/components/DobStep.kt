package com.bharat.app5.feature_auth.presentation.register.components

import android.icu.util.LocaleData
import android.os.Build
import android.widget.NumberPicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.viewinterop.AndroidView
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
import java.time.YearMonth
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DobStep(modifier: Modifier = Modifier, viewModel: RegisterViewModel, uiState : RegisterUiState) {
    val dob = uiState.userDetails.dob

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        RegistrationStepHolder(
            modifier = Modifier.align(Alignment.TopCenter),
            primaryText = "Enter your DOB please?"
        ) {

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 26.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Month Wheel
                AndroidView(
                    factory = { context ->
                        NumberPicker(context).apply {
                            minValue = 1
                            maxValue = 12
                            value = dob.monthValue
                            wrapSelectorWheel = true
                            setOnValueChangedListener { _, _, newValue ->
                                val newDob = safeDate(
                                    dob.dayOfMonth,
                                    newValue,
                                    dob.year

                                )
                                viewModel.onDobChanged(newDob)

                            }

                        }
                    },

                )
                // Day Wheel
                AndroidView(
                    factory = { context ->
                        NumberPicker(context).apply {
                            minValue = 1
                            maxValue = dob.dayOfMonth
                            value = dob.dayOfMonth
                            wrapSelectorWheel = true
                            setOnValueChangedListener { _, _, newValue ->
                                val newDob = safeDate(
                                    newValue,
                                    dob.monthValue,
                                    dob.year
                                )
                                viewModel.onDobChanged(newDob)
                            }
                        }
                    },

                )
                //Year Wheel
                AndroidView(
                    factory = { context ->
                        NumberPicker(context).apply{
                            minValue = 1950
                            maxValue = 2010
                            value = dob.year
                            wrapSelectorWheel = true
                            setOnValueChangedListener { _, _, newValue ->
                                val newDob = safeDate(
                                    dob.dayOfMonth,
                                    dob.monthValue,
                                    newValue
                                )
                                viewModel.onDobChanged(newDob)
                            }
                        }
                    },

                )

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
fun safeDate(day : Int, month : Int, year : Int): LocalDate{
    val monthLength = YearMonth.of(year, month).lengthOfMonth()
    val day = minOf(day, monthLength)
    return LocalDate.of(year, month, day)

}
