package com.bharat.app5.feature_auth.presentation.register.components

import android.service.autofill.OnClickAction
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bharat.app5.R
import com.google.firebase.annotations.concurrent.Background

@Composable
fun AuthButton(
    modifier: Modifier,
    borderColor : Color = Color.White,
    backgroundColor : Color = Color.White,
    @DrawableRes iconRes : Int,
    text : String,
    contentColor : Color = Color.Black,
    onClick : () -> Unit,
    shape : Shape

) {
    Box(
        modifier = modifier
            .height(50.dp)
            .clip(shape)
            .border(1.dp, borderColor, shape)
            .clickable(onClick = onClick)
            .background(backgroundColor)
            .padding(horizontal = 18.dp),
        contentAlignment = Alignment.Center
    ){
        Image(
            painterResource(iconRes),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterStart).size(20.dp)
        )

        Text(text, color = contentColor)

    }


}