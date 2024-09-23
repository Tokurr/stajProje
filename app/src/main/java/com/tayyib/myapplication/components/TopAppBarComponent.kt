package com.tayyib.myapplication.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tayyib.myapplication.R
import com.tayyib.myapplication.ui.theme.bimserColor

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(
    name: String,
    assignedUser: String,
    subject: String,
    message: String,
    onNavigationIconClick: () -> Unit,
    onPersonClick: () -> Unit,
    onMenuClick: () -> Unit
) {

    LargeTopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color(0XFF1D1B20),
        ),


        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(

                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description",
                    tint = bimserColor,
                    modifier = Modifier.size(20.dp)

                )
            }
        },
        actions = {

            IconButton(onClick = onPersonClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_person),
                    contentDescription = "Localized description",
                    tint = bimserColor,
                    modifier = Modifier.size(20.dp)

                )
            }
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description",
                    tint = bimserColor,
                    modifier = Modifier.size(20.dp)
                )
            }

        },

        title = {
            Column(modifier = Modifier.fillMaxWidth()) {


                Text(
                    name, fontSize = 14.sp, fontWeight = FontWeight.Bold, maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    assignedUser, fontSize = 14.sp, maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    "Konu : $subject", fontSize = 14.sp, maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )


            }


        },


        )

}

@Preview(showBackground = true)
@Composable
fun PreviewTopAppBar() {
    TopAppBarComponent(
        "BİMSER ÇÖZÜM YAZILIM VE DANIŞMANLIK",
        "Halil İbrahim Yanık",
        "Mobil Test Başlık",
        "Soru",
        {},
        {},
        {})

}