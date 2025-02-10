package com.cggcaio.core.theme.custom

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color
import com.cggcaio.core.theme.constants.blue100
import com.cggcaio.core.theme.constants.blue200
import com.cggcaio.core.theme.constants.blue300
import com.cggcaio.core.theme.constants.gray200
import com.cggcaio.core.theme.constants.gray300
import com.cggcaio.core.theme.constants.gray500
import com.cggcaio.core.theme.constants.gray600
import com.cggcaio.core.theme.constants.gray800
import com.cggcaio.core.theme.constants.gray900
import com.cggcaio.core.theme.constants.green100
import com.cggcaio.core.theme.constants.green200
import com.cggcaio.core.theme.constants.lightGray100
import com.cggcaio.core.theme.constants.orange100
import com.cggcaio.core.theme.constants.orange200
import com.cggcaio.core.theme.constants.orange300
import com.cggcaio.core.theme.constants.primary100
import com.cggcaio.core.theme.constants.primary200
import com.cggcaio.core.theme.constants.red100
import com.cggcaio.core.theme.constants.red200
import com.cggcaio.core.theme.constants.red300
import com.cggcaio.core.theme.constants.secondary

@Immutable
class CustomColorScheme(
    primary100: Color,
    primary200: Color,
    secondary: Color,
    background: Color,
    boxBackground: Color,
    textMuted: Color,
    textHeader: Color,
    textBody: Color,
    textWhite: Color,
    red100: Color,
    red200: Color,
    red300: Color,
    blue100: Color,
    blue200: Color,
    blue300: Color,
    orange100: Color,
    orange200: Color,
    orange300: Color,
    green100: Color,
    green200: Color,
    gray300: Color,
) {
    var primary100 by mutableStateOf(primary100, structuralEqualityPolicy())
        internal set
    var primary200 by mutableStateOf(primary200, structuralEqualityPolicy())
        internal set
    var secondary by mutableStateOf(secondary, structuralEqualityPolicy())
        internal set
    var background by mutableStateOf(background, structuralEqualityPolicy())
        internal set
    var boxBackground by mutableStateOf(boxBackground, structuralEqualityPolicy())
        internal set
    var textMuted by mutableStateOf(textMuted, structuralEqualityPolicy())
        internal set
    var textHeader by mutableStateOf(textHeader, structuralEqualityPolicy())
        internal set
    var textBody by mutableStateOf(textBody, structuralEqualityPolicy())
        internal set
    var textWhite by mutableStateOf(textWhite, structuralEqualityPolicy())
        internal set
    var red100 by mutableStateOf(red100, structuralEqualityPolicy())
        internal set
    var red200 by mutableStateOf(red200, structuralEqualityPolicy())
        internal set
    var red300 by mutableStateOf(red300, structuralEqualityPolicy())
        internal set
    var blue100 by mutableStateOf(blue100, structuralEqualityPolicy())
        internal set
    var blue200 by mutableStateOf(blue200, structuralEqualityPolicy())
        internal set
    var blue300 by mutableStateOf(blue300, structuralEqualityPolicy())
        internal set
    var orange100 by mutableStateOf(orange100, structuralEqualityPolicy())
        internal set
    var orange200 by mutableStateOf(orange200, structuralEqualityPolicy())
        internal set
    var orange300 by mutableStateOf(orange300, structuralEqualityPolicy())
        internal set
    var green100 by mutableStateOf(green100, structuralEqualityPolicy())
        internal set
    var green200 by mutableStateOf(green200, structuralEqualityPolicy())
        internal set
    var gray300 by mutableStateOf(gray300, structuralEqualityPolicy())
        internal set
}

fun lightScheme() =
    CustomColorScheme(
        primary100 = primary100,
        primary200 = primary200,
        secondary = secondary,
        background = lightGray100,
        boxBackground = Color.White,
        textMuted = gray600,
        textHeader = gray900,
        textBody = gray800,
        textWhite = Color.White,
        red100 = red100,
        red200 = red200,
        red300 = red300,
        blue100 = blue100,
        blue200 = blue200,
        blue300 = blue300,
        orange100 = orange100,
        orange200 = orange200,
        orange300 = orange300,
        green100 = green100,
        green200 = green200,
        gray300 = gray300,
    )

fun darkScheme() =
    CustomColorScheme(
        primary100 = primary100,
        primary200 = primary200,
        secondary = secondary,
        background = gray900,
        boxBackground = gray800,
        textMuted = gray500,
        textHeader = gray300,
        textBody = gray200,
        textWhite = Color.White,
        red100 = red100,
        red200 = red200,
        red300 = red300,
        blue100 = blue100,
        blue200 = blue200,
        blue300 = blue300,
        orange100 = orange100,
        orange200 = orange200,
        orange300 = orange300,
        green100 = green100,
        green200 = green200,
        gray300 = gray300,
    )

internal val LocalColorScheme = staticCompositionLocalOf { lightScheme() }
