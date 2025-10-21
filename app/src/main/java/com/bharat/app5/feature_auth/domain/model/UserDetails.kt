package com.bharat.app5.feature_auth.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

enum class Gender( val disPlayName : String){ MALE("Male"), FEMALE("Female")}
enum class Goal(val displayName : String) { LOSE_WEIGHT("Lose Weight"), GAIN_WEIGHT("Gain Weight"), MAINTAIN_WEIGHT("Maintain Weight") }

data class UserDetails @RequiresApi(Build.VERSION_CODES.O) constructor(
    val gender : Gender? = null,
    val goal : Goal? = null,
    val name : String? = "",
    val dob : LocalDate = LocalDate.of(2000,1,1),
    val height : Double? = 0.0,
    val weight : Double? = 0.0
)
