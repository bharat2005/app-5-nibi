package com.bharat.app5.feature_auth.presentation.register.components

import android.R
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import java.time.Year
import java.time.YearMonth
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DobStep(modifier: Modifier = Modifier, viewModel: RegisterViewModel, uiState : RegisterUiState) {
    val dob = uiState.userDetails.dob

    var selectedDay by remember { mutableStateOf(dob.dayOfMonth) }
    var selectedMonth by remember { mutableStateOf(dob.monthValue)}
    var selectedYear by remember { mutableStateOf(dob.year)}

    LaunchedEffect(dob) {
        selectedYear = dob.year
        selectedMonth = dob.monthValue
        selectedDay = dob.dayOfMonth
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ){
        RegistrationStepHolder(
            modifier = Modifier.align(Alignment.TopCenter),
            primaryText = "Enter your DOB please?"
        ) {

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 38.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Month Wheel
                AndroidView(
                    factory = { context ->
                        NumberPicker(context).apply {
                            val monthNames = arrayOf(
                                "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
                            )
                            minValue = 1
                            maxValue = 12
                            displayedValues = monthNames
                            value = selectedMonth
                            wrapSelectorWheel = true
                            setOnValueChangedListener { _, _, newValue ->
                                selectedMonth = newValue
                                val newDob = safeDate(
                                    selectedDay,
                                    newValue,
                                    selectedYear

                                )
                                viewModel.onDobChanged(newDob)

                            }

                        }
                    },
                    update = { picker ->
                        if(picker.value != selectedMonth) picker.value = selectedMonth
                    }
                )
                // Day Wheel
                AndroidView(
                    factory = { context ->
                        NumberPicker(context).apply {
                            minValue = 1
                            maxValue = YearMonth.of(selectedYear, selectedMonth).lengthOfMonth()
                            value = selectedDay
                            wrapSelectorWheel = true
                            setOnValueChangedListener { _, _, newValue ->
                                selectedDay = newValue
                                val newDob = safeDate(
                                    newValue,
                                    selectedMonth,
                                    selectedYear
                                )
                                viewModel.onDobChanged(newDob)
                            }
                        }
                    },
                    update = {picker ->
                        if(picker.maxValue != YearMonth.of(selectedYear, selectedMonth).lengthOfMonth()) picker.maxValue = YearMonth.of(selectedYear, selectedMonth).lengthOfMonth()
                        if(picker.value != selectedDay) picker.value = selectedDay
                    }
                )
                //Year Wheel
                AndroidView(
                    factory = { context ->
                        NumberPicker(context).apply{
                            minValue = 1950
                            maxValue = 2010
                            value = selectedYear
                            wrapSelectorWheel = true
                            setOnValueChangedListener { _, _, newValue ->
                                val newDob = safeDate(
                                    selectedDay,
                                    selectedMonth,
                                    newValue
                                )
                                viewModel.onDobChanged(newDob)
                            }
                        }
                    },
                    update = {picker ->
                        if(picker.value != selectedYear) picker.value = selectedYear
                    }
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
