package com.tayyib.myapplication.ui.view

import android.annotation.SuppressLint
import androidx.compose.ui.graphics.Color

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tayyib.myapplication.components.BottomBar
import com.tayyib.myapplication.viewmodel.LoginViewModel
import androidx.compose.ui.unit.sp
import com.tayyib.myapplication.R
import com.tayyib.myapplication.ui.theme.bimserColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import com.tayyib.myapplication.components.ButtonComponent
import com.tayyib.myapplication.screen.Screen
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.graphics.ColorFilter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun ProfileScreen(navController: NavHostController) {
    val viewModel: LoginViewModel = hiltViewModel()
    val loginResponse = viewModel.getLoginResponse()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {

            BottomBar(navController = navController)
        }

    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageModifier = Modifier
                .padding(top = 100.dp)
                .size(150.dp)

            Image(
                painter = painterResource(id = R.drawable.user1),
                colorFilter = ColorFilter.tint(bimserColor),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = imageModifier
            )


            Text(
                modifier = Modifier
                    .padding(top = 60.dp),
                text = loginResponse.firstname,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = bimserColor

            )

            Text(
                modifier = Modifier
                    .padding(top = 25.dp),
                text = loginResponse.lastname,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = bimserColor
            )

            Text(
                modifier = Modifier
                    .padding(top = 25.dp),
                text = loginResponse.email,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = bimserColor
            )

            Spacer(modifier = Modifier.height(30.dp))

            ButtonComponent(onClick = {

                navController.popBackStack()
                navController.navigate(route = Screen.Login.route)

            }, buttonText = "Çıkış Yap", buttonColor = bimserColor, textColor = Color.White)


        }
    }


}

@Composable
@Preview(showBackground = true)
fun PreviewProfileScreen() {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}