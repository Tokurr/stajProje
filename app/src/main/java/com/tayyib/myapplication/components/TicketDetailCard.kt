package com.tayyib.myapplication.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tayyib.myapplication.R


@Composable
fun TicketDetailCard(sentDate: String?, senderUserName: String?, name: String?, assignedUserName: String?, message: String?, onIconClickListener: () -> Unit) {
    ElevatedCard(modifier = Modifier.fillMaxWidth().heightIn(min = 200.dp).padding(10.dp), shape = CardDefaults.shape, colors = CardDefaults.outlinedCardColors(containerColor = Color.White,), elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),) { Column(modifier = Modifier.fillMaxSize().padding(15.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.padding(top = 10.dp), text = senderUserName ?: "Bilgi yok", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1f))
                Icon(painter = painterResource(id = R.drawable.eye), contentDescription = "", modifier = Modifier.padding(top = 8.dp).size(24.dp).clickable(onClick = onIconClickListener)) }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(painter = painterResource(id = R.drawable.ic_status), contentDescription = "", modifier = Modifier.size(14.dp))
                Text(modifier = Modifier.padding(horizontal = 10.dp), text = name ?: "Bilgi yok", color = Color.Gray, fontSize = 12.sp)
                Icon(painter = painterResource(id = R.drawable.ic_date), contentDescription = "", modifier = Modifier.size(14.dp))
                Text(modifier = Modifier.padding(horizontal = 10.dp), text = sentDate ?: "Bilgi yok", color = Color.Gray, fontSize = 12.sp) }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(painter = painterResource(id = R.drawable.ic_person), contentDescription = "", modifier = Modifier.size(14.dp))
                Text(modifier = Modifier.padding(horizontal = 10.dp), text = "Atanan kullanıcı: ${assignedUserName ?: "Bilgi yok"}", fontSize = 12.sp) }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(painter = painterResource(id = R.drawable.ic_message), contentDescription = "", modifier = Modifier.size(14.dp))
                Text(modifier = Modifier.padding(horizontal = 10.dp), text = "Mesaj: ${message ?: "Bilgi yok"}", fontSize = 12.sp)
            } } } }


@Composable
@Preview(showBackground = true)
fun TicketDetailPreview() {
    TicketDetailCard("deneme", "deneme", "deneme", "deneme", "deneme")
    {

    }
}