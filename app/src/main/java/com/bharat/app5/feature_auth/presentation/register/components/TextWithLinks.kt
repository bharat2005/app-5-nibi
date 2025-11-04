package com.bharat.app5.feature_auth.presentation.register.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun TextWithLinks(
    onTermsClick : () -> Unit,
    onPrivacyPolicyClick : () -> Unit,
    onExternalTransmissionClick : () -> Unit,
) {
    val annotedText = buildAnnotatedString {
        append("I agree to the ")

        pushStringAnnotation("TERMS", annotation = "terms")
        withStyle(SpanStyle(textDecoration = TextDecoration.Underline, color = Color.Blue)){
            append("terms of use ")
        }
        pop()

        append(", ")

        pushStringAnnotation(tag = "PRIVACY", annotation = "privacy")
        withStyle(SpanStyle(textDecoration = TextDecoration.Underline, color = Color.Blue)){
            append("privacy policy")
        }
        pop()

        append("and ")

        pushStringAnnotation(tag = "EXTERANL_TRANSMISSIONS", annotation = "external_transmissions")
        withStyle(SpanStyle(textDecoration = TextDecoration.Underline, color = Color.Blue)){
            append("external transmissions ")
        }
        pop()

        append("of user information.")

    }

    ClickableText(
        text = annotedText,
        onClick = { offset ->
            annotedText.getStringAnnotations("TERMS", offset, offset)
                .firstOrNull()?.let { onTermsClick() }

            annotedText.getStringAnnotations("PRIVACY", offset, offset)
                .firstOrNull()?.let { onPrivacyPolicyClick() }

            annotedText.getStringAnnotations("EXTERANL_TRANSMISSIONS", offset, offset)
                .firstOrNull()?.let { onExternalTransmissionClick() }
        },
        style = TextStyle(
            color = Color.Black,
            fontSize = 14.sp
        )
    )
}