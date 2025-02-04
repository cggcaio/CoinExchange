package com.cggcaio.coinexchange.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import com.cggcaio.coinexchange.ui.theme.AppTheme.color
import com.cggcaio.coinexchange.ui.theme.AppTheme.typography
import com.cggcaio.coinexchange.ui.theme.custom.CustomColorScheme
import com.cggcaio.coinexchange.ui.theme.custom.CustomTypography
import com.cggcaio.coinexchange.ui.theme.custom.LocalColorScheme
import com.cggcaio.coinexchange.ui.theme.custom.LocalTypography
import com.cggcaio.coinexchange.ui.theme.custom.darkScheme
import com.cggcaio.coinexchange.ui.theme.custom.lightScheme
import com.cggcaio.coinexchange.ui.theme.custom.toCustomTypography

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
