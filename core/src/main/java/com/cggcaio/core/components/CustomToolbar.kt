package com.cggcaio.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cggcaio.core.theme.AppTheme.color
import com.cggcaio.core.theme.AppTheme.typography
import com.cggcaio.core.utils.noRippleClickable

@Composable
fun CustomToolbar(
    title: String? = "",
    startIcon: ImageVector? = null,
    onClickStartIcon: (() -> Unit)? = null,
) {
    Row(
        modifier =
            Modifier
                .background(color = color.background)
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        startIcon?.let { safeIcon ->
            CustomIcon(
                modifier = Modifier.testTag(tag = "ToolbarBackPress"),
                icon = startIcon,
                onClick = onClickStartIcon,
            )
        }
        title?.let { safeTitle ->
            Spacer(modifier = Modifier.size(size = 16.dp))
            Text(
                modifier = Modifier.weight(weight = 1f),
                text = safeTitle,
                style = typography.header3,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.size(size = 16.dp))
        }
    }
}

@Composable
private fun CustomIcon(
    modifier: Modifier,
    icon: ImageVector,
    onClick: (() -> Unit)?,
) {
    Box(
        modifier =
            modifier
                .noRippleClickable { onClick?.invoke() }
                .size(size = 32.dp)
                .background(
                    color = color.boxBackground,
                    shape = CircleShape,
                ),
    ) {
        Icon(
            modifier =
                Modifier
                    .size(size = 24.dp)
                    .align(alignment = Alignment.Center),
            imageVector = icon,
            tint = color.textBody,
            contentDescription = "Start Icon",
        )
    }
}
