package com.tayyib.myapplication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tayyib.myapplication.ui.theme.bimserColor

@Composable
fun ChatBox(onSendChatClickListener: (String) -> Unit, onIconClickListener: (String) -> Unit) {
    var chatBoxValue by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            singleLine = true, value = chatBoxValue, onValueChange = { newText -> chatBoxValue = newText },
            placeholder = {
                Text(text = "Mesaj") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = bimserColor, unfocusedBorderColor = Color.Black, cursorColor = Color.Gray),
            shape = RoundedCornerShape(10.dp),
            trailingIcon = {
                IconButton(
                    onClick = { onIconClickListener(chatBoxValue.text)
                        onSendChatClickListener(chatBoxValue.text)
                        chatBoxValue = TextFieldValue("") })
                {
                    Icon( Icons.Outlined.Send,"")
                } },
                    modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp,bottom = 50.dp))
}

@Preview(showBackground = true)
@Composable
fun ChatBoxPreview()
{

}
