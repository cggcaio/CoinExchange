package com.cggcaio.coinexchange.core.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.cggcaio.coinexchange.ui.theme.AppTheme.color

private const val DEFAULT_REPETITIONS = 1

@Composable
fun CustomShimmer(
    modifier: Modifier = Modifier,
    repeats: Int = DEFAULT_REPETITIONS,
    shimmerColors: List<Color> =
        listOf(
            color.blue200.copy(alpha = 0.4f),
            color.blue200.copy(alpha = 0.1f),
            color.blue200.copy(alpha = 0.4f),
        ),
    shimmerItem: @Composable (brush: Brush) -> Unit,
) {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim =
        transition.animateFloat(
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec =
                infiniteRepeatable(
                    animation =
                        tween(
                            durationMillis = 1000,
                            easing = FastOutSlowInEasing,
                        ),
                    repeatMode = RepeatMode.Reverse,
                ),
            label = "shimmer",
        )

    val brush =
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnim.value, y = translateAnim.value),
        )

    Column(modifier = modifier) {
        repeat(times = repeats) {
            shimmerItem(brush)
        }
    }
}
