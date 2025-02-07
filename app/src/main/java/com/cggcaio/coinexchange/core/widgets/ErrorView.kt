package com.cggcaio.coinexchange.core.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cggcaio.coinexchange.core.components.CustomButton
import com.cggcaio.coinexchange.ui.theme.AppTheme.color
import com.cggcaio.coinexchange.ui.theme.AppTheme.typography

@Composable
fun ErrorView(retry: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().testTag(tag = "ErrorView")) {
        Column(
            modifier =
                Modifier
                    .background(color = color.background)
                    .fillMaxSize()
                    .weight(weight = 1f)
                    .padding(vertical = 16.dp),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .weight(weight = 1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Error Icon",
                    tint = color.red300,
                    modifier = Modifier.size(size = 48.dp),
                )
                Spacer(modifier = Modifier.size(size = 8.dp))

                Text(
                    text = "Algo deu errado",
                    style = typography.header3,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.size(size = 8.dp))
                Text(
                    text =
                        "Estamos enfrentando dificuldades.\n" +
                            "Por favor, tente novamente mais tarde.",
                    style = typography.body,
                    textAlign = TextAlign.Center,
                )
            }

            CustomButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Tentar Novamente",
                onClick = {
                    retry()
                },
            )
        }
    }
}

@Preview
@Composable
fun ErrorViewPreview() {
    ErrorView { }
}
