package com.bharat.app5.feature_auth.presentation.register.components

import android.os.Build
import android.widget.NumberPicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.bharat.app5.feature_auth.presentation.components.RegistrationStepHolder
import com.bharat.app5.feature_auth.presentation.register.RegisterUiState
import com.bharat.app5.feature_auth.presentation.register.StartViewModel
import java.time.LocalDate
import java.time.YearMonth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DobStep(modifier: Modifier = Modifier, viewModel: StartViewModel, uiState : RegisterUiState) {
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
