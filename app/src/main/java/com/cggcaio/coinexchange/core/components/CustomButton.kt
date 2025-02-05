package com.cggcaio.coinexchange.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cggcaio.coinexchange.core.utils.noRippleClickable
import com.cggcaio.coinexchange.ui.theme.AppTheme.color
import com.cggcaio.coinexchange.ui.theme.AppTheme.typography
import com.cggcaio.coinexchange.ui.theme.constants.gray600
import com.cggcaio.coinexchange.ui.theme.constants.gray800

@Composable
fun CustomButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onClickDisabled: () -> Unit = {},
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    enabled: Boolean = true,
    loading: Boolean = false,
    style: CustomButtonStyles = CustomButtonStyles.SECONDARY,
) {
    Box {
        Button(
            modifier = modifier.height(height = 48.dp),
            border = BorderStroke(width = 1.dp, color = style.outlineColor),
            colors =
                ButtonDefaults
                    .buttonColors(containerColor = style.backgroundColor),
            enabled = (enabled && !loading),
            onClick = onClick,
        ) {
            if (loading) {
                Box {
                    Text(
                        text = text,
                        style = typography.body.copy(color = Color.Transparent),
                    )
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp).align(alignment = Alignment.Center),
                        color =
                            when (style) {
                                CustomButtonStyles.SECONDARY -> gray800
                                else -> style.contentColor
                            },
                        strokeWidth = 2.dp,
                    )
                }
                return@Button
            }
            val contentColor =
                if (enabled) {
                    when (style) {
                        CustomButtonStyles.SECONDARY -> {
                            gray800
                        }

                        CustomButtonStyles.PRIMARY -> {
                            color.textWhite
                        }

                        else -> {
                            style.contentColor
                        }
                    }
                } else {
                    gray600
                }
            startIcon?.let { safeIcon ->
                Icon(
                    imageVector = safeIcon,
                    modifier = Modifier.size(size = 20.dp),
                    tint = contentColor,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.size(size = 12.dp))
            }

            Text(
                text = text,
                style = typography.body.copy(color = contentColor),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
            )
            endIcon?.let { safeIcon ->
                Spacer(modifier = Modifier.size(size = 12.dp))
                Icon(
                    imageVector = safeIcon,
                    modifier = Modifier.size(size = 20.dp),
                    tint = contentColor,
                    contentDescription = null,
                )
            }
        }

        if (!enabled) {
            Box(modifier = Modifier.height(height = 48.dp).noRippleClickable { onClickDisabled() })
        }
    }
}

enum class CustomButtonStyles {
    PRIMARY,
    SECONDARY,
    GHOST,
    OUTLINED,
    DELETE,
    ;

    val backgroundColor: Color
        @Composable
        @ReadOnlyComposable
        get() =
            when (this) {
                PRIMARY -> color.primary100
                SECONDARY -> color.secondary
                GHOST -> Color.Transparent
                OUTLINED -> Color.Transparent
                DELETE -> Color.Transparent
            }

    val outlineColor: Color
        @Composable
        @ReadOnlyComposable
        get() =
            when (this) {
                PRIMARY, SECONDARY, GHOST -> Color.Unspecified
                OUTLINED -> color.secondary
                DELETE -> color.red300
            }

    val contentColor: Color
        @Composable
        @ReadOnlyComposable
        get() =
            when (this) {
                PRIMARY -> color.textBody
                SECONDARY -> color.textWhite
                GHOST -> color.textBody
                OUTLINED -> color.textBody
                DELETE -> color.red300
            }
}

@Preview(showBackground = true)
@Composable
fun CustomButtonPreview() {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        CustomButton(
            text = "Primary Button",
            onClick = {},
            style = CustomButtonStyles.PRIMARY,
        )

        CustomButton(
            text = "Primary with Icon",
            onClick = {},
            startIcon = Icons.Default.Check,
            style = CustomButtonStyles.PRIMARY,
        )

        CustomButton(
            text = "Primary Disabled",
            onClick = {},
            enabled = false,
            style = CustomButtonStyles.PRIMARY,
        )

        CustomButton(
            text = "Primary Loading",
            onClick = {},
            loading = true,
            style = CustomButtonStyles.PRIMARY,
        )

        CustomButton(
            text = "Secondary Button",
            onClick = {},
            style = CustomButtonStyles.SECONDARY,
        )

        CustomButton(
            text = "Secondary with Icon",
            onClick = {},
            endIcon = Icons.Default.Close,
            style = CustomButtonStyles.SECONDARY,
        )

        CustomButton(
            text = "Secondary Disabled",
            onClick = {},
            enabled = false,
            style = CustomButtonStyles.SECONDARY,
        )

        CustomButton(
            text = "Secondary Loading",
            onClick = {},
            loading = true,
            style = CustomButtonStyles.SECONDARY,
        )

        CustomButton(
            text = "Ghost Button",
            onClick = {},
            style = CustomButtonStyles.GHOST,
        )

        CustomButton(
            text = "Ghost with Icon",
            onClick = {},
            startIcon = Icons.Default.Check,
            style = CustomButtonStyles.GHOST,
        )

        CustomButton(
            text = "Ghost Disabled",
            onClick = {},
            enabled = false,
            style = CustomButtonStyles.GHOST,
        )

        CustomButton(
            text = "Outlined Button",
            onClick = {},
            style = CustomButtonStyles.OUTLINED,
        )

        CustomButton(
            text = "Outlined with Icon",
            onClick = {},
            endIcon = Icons.Default.Close,
            style = CustomButtonStyles.OUTLINED,
        )

        CustomButton(
            text = "Outlined Disabled",
            onClick = {},
            enabled = false,
            style = CustomButtonStyles.OUTLINED,
        )

        CustomButton(
            text = "Delete Button",
            onClick = {},
            style = CustomButtonStyles.DELETE,
        )

        CustomButton(
            text = "Delete with Icon",
            onClick = {},
            startIcon = Icons.Default.Close,
            style = CustomButtonStyles.DELETE,
        )

        CustomButton(
            text = "Delete Disabled",
            onClick = {},
            enabled = false,
            style = CustomButtonStyles.DELETE,
        )
    }
}
