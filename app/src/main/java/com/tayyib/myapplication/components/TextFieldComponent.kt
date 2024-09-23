package com.tayyib.myapplication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import com.tayyib.myapplication.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(
    placeholder: String,
    passwordVisible: Boolean,
    trailingIconResId: Int? = null,
    underlineColor: Color = Color.Gray.copy(alpha = 0.38f),
    value: String,
    onValueChange: (String) -> Unit
) {

    var passwordVisibleClicked by remember { mutableStateOf(passwordVisible) }
    var trailingIcon by remember { mutableStateOf(trailingIconResId) }

    OutlinedTextField(shape = RoundedCornerShape(10.dp),
        value = value,
        singleLine = true,
        onValueChange = onValueChange,

        placeholder = {
            Text(text = placeholder, color = underlineColor)
        },
        trailingIcon = if (trailingIconResId != null) {
            {
                if (trailingIconResId == R.drawable.eye) {
                    trailingIcon = if (passwordVisibleClicked) {
                        R.drawable.eye
                    } else {
                        R.drawable.eye_invisible_svgrepo_com
                    }
                }
                Icon(
                    painter = trailingIcon?.let { painterResource(id = it) }!!,
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp)
                        .clickable { passwordVisibleClicked = !passwordVisibleClicked },
                )
            }
        } else null,
        visualTransformation = if (passwordVisibleClicked) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = underlineColor,
            cursorColor = underlineColor
        ))
}