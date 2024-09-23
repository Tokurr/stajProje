package com.tayyib.myapplication.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tayyib.myapplication.R
import com.tayyib.myapplication.screen.Screen
import com.tayyib.myapplication.ui.theme.bimserColor

@Composable
fun TicketCard(
    name: String?,
    sentDate: String?,
    subject: String?,
    message: String?,
    products: String?,
    assignedUser: String?,
    ticketImportanceLevels: String?,
    ticketTypes: String?,
    ticketStatus: String?,
    tickedId: String?,
    navController: NavHostController
) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 200.dp)
            .padding(12.dp)
            .clickable {

                println(ticketStatus)
                navController.popBackStack()
                navController.navigate(Screen.TicketDetail.route + "/$tickedId" + "/$name" + "/$assignedUser" + "/$subject" + "/$message")

            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    if (name != null) {
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = name,
                            fontSize = 15.sp,
                        )
                    }
                }
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp),
                    tint = bimserColor
                )
            }

            InfoRow(info = sentDate ?: "bilgi yok", R.drawable.ic_date, "Gönderim Tarihi")

            InfoRow(info = subject ?: "bilgi yok", R.drawable.ic_subject, "Konu")
            InfoRow(info = message ?: "bilgi yok", R.drawable.ic_message, "Mesaj")


            Spacer(modifier = Modifier.height(5.dp))
            Divider(color = Color.Gray, thickness = 0.4.dp)
            Spacer(modifier = Modifier.height(10.dp))

            InfoRow(info = products ?: "bilgi yok", R.drawable.ic_product, "Ürün")
            InfoRow(info = assignedUser ?: "bilgi yok", R.drawable.ic_person, "Atanan Kullanıcı")
            InfoRow(
                info = ticketImportanceLevels ?: "bilgi yok",
                R.drawable.ic_important,
                "Önem Seviyesi"
            )

            InfoRow(info = ticketTypes ?: "bilgi yok", R.drawable.ic_status, "Tür")
            InfoRow(info = ticketStatus ?: "bilgi yok", R.drawable.ic_flag, "Durum")

        }
    }
}

@Composable
fun InfoRow(info: String? = "bilgi yok", iconResId: Int?, text: String? = "bilgi yok") {
    Row(modifier = Modifier.fillMaxWidth()) {


        iconResId?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )

        }
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = "$text: ",
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            fontSize = 16.sp
        )

        Text(
            modifier = Modifier.padding(start = 5.dp),
            text = "$info",
            fontWeight = FontWeight.Light,
            color = Color.Gray,
            fontSize = 16.sp
        )

    }

}
@Composable
@Preview(showBackground = true)
fun TicketCardPreview() {
    val navController = rememberNavController()
    TicketCard(
        "BİMSER YAZILIM VE ÇÖZÜM",
        "Jul 25, 2024 at 3:11PM",
        "Mobil Test Başlık",
        "Soru",
        "CSP",
        "Halil İbrahim Yanık",
        "Low",
        "Problem",
        "Open",
        "213",
        navController
    )

}