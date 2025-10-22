package com.bharat.app5.feature_auth.presentation.register.components

import android.R
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.TextToolbar
import androidx.compose.ui.unit.dp
import com.bharat.app5.feature_auth.domain.model.Goal
import com.bharat.app5.feature_auth.presentation.components.RegistrationStepHolder
import com.bharat.app5.feature_auth.presentation.register.RegisterViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GoalStep(modifier: Modifier = Modifier, viewModel: RegisterViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val unSupportedGoal by viewModel.unSupportedGoal.collectAsState()

     Box(
         modifier = Modifier.fillMaxSize()
     ){
         RegistrationStepHolder(
             primaryText = "Select your goal, Pleasee?",
             stepIconRes = 12,
             modifier = Modifier.align(Alignment.TopCenter)
         )
         {
             Column(
                 modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                 verticalArrangement = Arrangement.spacedBy(18.dp),
                 horizontalAlignment = Alignment.CenterHorizontally
             ) {
                 Goal.entries.forEachIndexed { index, goal ->
                     val isSelected = uiState.userDetails.goal == goal
                     val backgroundColor = if(isSelected) MaterialTheme.colorScheme.surface else Color.White
                     val borderColor = if(isSelected) MaterialTheme.colorScheme.primary else Color.LightGray

                     Box(
                         modifier = Modifier
                             .fillMaxWidth()
                             .height(60.dp)
                             .clip(RoundedCornerShape(24.dp))
                             .border(shape = RoundedCornerShape(24.dp), width = 1.dp, color = borderColor)
                             .clickable(onClick = {viewModel.onGoalSelected(goal)})
                             .background(backgroundColor)
                     ){
                         Row(
                             modifier = Modifier.fillMaxSize(),
                             verticalAlignment = Alignment.CenterVertically,
                             horizontalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterHorizontally)
                         ) {
                             Box(modifier = Modifier.size(18.dp).background(Color.Black))
                             Text(goal.displayName)

                         }
                     }
                 }

             }
         }

         Button(
             onClick = {viewModel.goToNextStep()},
             modifier = Modifier.fillMaxWidth( ).align(Alignment.BottomCenter)
         )
         {
             Text("Next")
         }

         if(unSupportedGoal != null){
             AlertDialog(
                 title = {Text("UnSupported Goal")},
                 text = { Text("Currently we only support weight loss plans. Selecting ${unSupportedGoal} will default to weight loss. Support for other goals is coming soon.") },
                 onDismissRequest = {},
                confirmButton = {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {viewModel.onDismissUnsupportedGoal()}
                    ) {
                        Text("Ok")
                 }
             }
             )
         }


     }

}