package com.cggcaio.coinexchange.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cggcaio.coinexchange.core.utils.noRippleClickable
import com.cggcaio.coinexchange.ui.theme.AppTheme.color
import com.cggcaio.coinexchange.ui.theme.AppTheme.typography
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false,
    placeholder: String = "",
    supportText: String? = null,
    validator: CustomTextFieldValidate? = null,
    isValid: ((Boolean) -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    singleLine: Boolean = true,
) {
    val defaultErrorMessage = "Campo inválido"
    val passwordVisible = remember { mutableStateOf(value = false) }
    val focusRequester = remember { FocusRequester() }
    val internalError = remember { mutableStateOf(value = false) }
    val validationJob = remember { mutableStateOf<Job?>(value = null) }
    val text = remember { mutableStateOf(value = value) }
    val internalValidation = remember { mutableStateOf(value = false) }

    LaunchedEffect(internalValidation.value) {
        isValid?.invoke(internalValidation.value)
    }

    LaunchedEffect(text.value) {
        validationJob.value?.cancel()
        validator?.let {
            if (text.value.isNotEmpty()) {
                validationJob.value =
                    launch {
                        delay(timeMillis = 400)
                        internalError.value =
                            when (it) {
                                CustomTextFieldValidate.NOT_EMPTY ->
                                    text.value.length < 3
                            }
                        internalValidation.value = !internalError.value
                    }
            }
        }
    }

    Column(
        modifier =
            modifier
                .noRippleClickable { focusRequester.requestFocus() }
                .fillMaxWidth()
                .background(
                    color = color.boxBackground,
                    shape = RoundedCornerShape(size = 24.dp),
                ).border(
                    width = 1.dp,
                    color =
                        when {
                            (isError || internalError.value) && errorMessage != null -> Color.Red
                            else -> Color.Unspecified
                        },
                    shape = RoundedCornerShape(size = 24.dp),
                ).padding(vertical = 12.dp, horizontal = 20.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(weight = 1f)) {
                Text(text = label, style = typography.bodySmall)
                Spacer(modifier = Modifier.size(size = 8.dp))
                BasicTextField(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                            .testTag(tag = "CustomTextField"),
                    value = text.value,
                    onValueChange = {
                        text.value = it
                        onValueChange(it)
                    },
                    visualTransformation =
                        when {
                            isPassword && !passwordVisible.value ->
                                PasswordVisualTransformation()

                            else ->
                                VisualTransformation.None
                        },
                    textStyle = typography.body,
                    singleLine = singleLine,
                    cursorBrush = SolidColor(value = color.secondary),
                )
            }
        }
    }

    if (
        (supportText != null || isError || (internalError.value && errorMessage != null)) &&
        text.value.isNotEmpty()
    ) {
        val icon =
            when {
                isError || internalError.value -> Icons.Default.Warning
                else -> Icons.Default.Info
            }
        val textColor =
            when {
                isError || internalError.value -> Color.Red
                else -> color.textBody
            }
        val displayText =
            when {
                errorMessage == null -> defaultErrorMessage
                isError || internalError.value -> errorMessage
                else -> supportText
            }

        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(18.dp),
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = displayText ?: "",
                style = typography.bodySmall,
                color = textColor,
            )
        }
    }
}

enum class CustomTextFieldValidate {
    NOT_EMPTY,
}

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
    ) {
        CustomTextField(
            value = "",
            onValueChange = {},
            label = "Nome",
            placeholder = "Digite seu nome",
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = "",
            onValueChange = {},
            label = "E-mail",
            placeholder = "Digite seu e-mail",
            supportText = "Exemplo: usuario@dominio.com",
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = "",
            onValueChange = {},
            label = "Senha",
            placeholder = "Digite sua senha",
            isPassword = true,
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = "000.000.000-00",
            onValueChange = {},
            label = "CPF",
            placeholder = "Digite seu CPF",
            isError = true,
            errorMessage = "CPF inválido",
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = "",
            onValueChange = {},
            label = "Endereço",
            placeholder = "Digite seu endereço",
            validator = CustomTextFieldValidate.NOT_EMPTY,
            errorMessage = "Este campo é obrigatório",
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = "",
            onValueChange = {},
            label = "Descrição",
            placeholder = "Digite uma descrição",
            singleLine = false,
        )
    }
}
