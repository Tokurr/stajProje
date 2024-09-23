package com.tayyib.myapplication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tayyib.myapplication.ui.theme.MyApplicationTheme
import com.tayyib.myapplication.ui.theme.bimserColor
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.draw.shadow



@Composable
fun ButtonComponent(onClick: () -> Unit,
             buttonText: String,
             height: Dp = ButtonDefaults.MinHeight,
             buttonColor: Color,
             textColor: Color
)
{
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .padding(horizontal = 50.dp, vertical = 25.dp)
            .height(height)
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(15.dp))
            .background(color = buttonColor),

        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ),
        content = {
            Text(
                text = buttonText,
                color = textColor,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    )
}
@Preview(showBackground = true)
@Composable
fun MyButtonPreview() {
    MyApplicationTheme {
        ButtonComponent(onClick = {

        },
            buttonText = "Giriş",
            buttonColor = Color.White,
            textColor = bimserColor
        )
    }
}
