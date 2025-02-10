package com.cggcaio.core.theme.custom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.cggcaio.core.theme.AppTheme.color

@Immutable
data class CustomTypography(
    val header1: TextStyle =
        TextStyle(fontSize = 32.sp),
    val header2: TextStyle =
        TextStyle(fontSize = 24.sp),
    val header3: TextStyle =
        TextStyle(fontSize = 18.sp),
    val header4: TextStyle =
        TextStyle(fontSize = 16.sp),
    val body: TextStyle =
        TextStyle(fontSize = 14.sp),
    val bodyBold: TextStyle =
        TextStyle(fontSize = 14.sp),
    val bodySmall: TextStyle =
        TextStyle(fontSize = 12.sp),
    val bodySmallBold: TextStyle =
        TextStyle(fontSize = 12.sp),
)

@Composable
fun CustomTypography.toCustomTypography(): CustomTypography =
    this.copy(
        header1 =
            this.header1.copy(
                color = color.textHeader,
            ),
        header2 =
            this.header2.copy(
                color = color.textHeader,
            ),
        header3 =
            this.header3.copy(
                color = color.textBody,
            ),
        header4 =
            this.header4.copy(
                color = color.textBody,
            ),
        body =
            this.body.copy(
                color = color.textBody,
            ),
        bodyBold =
            this.bodyBold.copy(
                color = color.textBody,
            ),
        bodySmall =
            this.bodySmall.copy(
                color = color.textMuted,
            ),
        bodySmallBold =
            this.bodySmallBold.copy(
                color = color.textMuted,
            ),
    )

internal val LocalTypography = staticCompositionLocalOf { CustomTypography() }
