package com.cggcaio.coinexchange.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cggcaio.coinexchange.ui.theme.AppTheme.color
import com.cggcaio.coinexchange.ui.theme.AppTheme.typography
import kotlinx.coroutines.delay

enum class SnackbarState {
    SUCCESS,
    INFO,
    ERROR,
    ALERT,
}

@Composable
fun CustomSnackbar(
    message: String,
    state: SnackbarState,
    durationMillis: Long = 5000,
    show: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(show) {
        if (show) {
            delay(durationMillis)
            onDismiss()
        }
    }

    if (show) {
        val backgroundColor: Color
        val contentColor: Color
        val icon =
            when (state) {
                SnackbarState.SUCCESS -> {
                    backgroundColor = color.green100
                    contentColor = color.textWhite
                    Icons.Default.CheckCircle
                }

                SnackbarState.INFO -> {
                    backgroundColor = color.gray300
                    contentColor = color.textWhite
                    Icons.Default.Info
                }

                SnackbarState.ERROR -> {
                    backgroundColor = color.red100
                    contentColor = color.textWhite
                    Icons.Default.Close
                }

                SnackbarState.ALERT -> {
                    backgroundColor = color.secondary
                    contentColor = color.gray300
                    Icons.Default.Warning
                }
            }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                modifier
                    .padding(16.dp)
                    .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(24.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = message,
                style = typography.body.copy(color = contentColor),
            )
        }
    }
}
