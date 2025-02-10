package com.cggcaio.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import com.cggcaio.core.theme.AppTheme.color
import com.cggcaio.core.theme.AppTheme.typography
import com.cggcaio.core.theme.custom.CustomColorScheme
import com.cggcaio.core.theme.custom.CustomTypography
import com.cggcaio.core.theme.custom.LocalColorScheme
import com.cggcaio.core.theme.custom.LocalTypography
import com.cggcaio.core.theme.custom.darkScheme
import com.cggcaio.core.theme.custom.lightScheme
import com.cggcaio.core.theme.custom.toCustomTypography

object AppTheme {
    val typography: CustomTypography
        @Composable
        get() = LocalTypography.current.toCustomTypography()

    val color: CustomColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current
}

@Composable
fun CoinExchangeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) darkScheme() else lightScheme()

    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalTypography provides typography,
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = color.background,
            content = content,
        )
    }
}
