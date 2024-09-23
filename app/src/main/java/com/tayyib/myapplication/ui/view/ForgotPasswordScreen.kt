package com.tayyib.myapplication.ui.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import com.tayyib.myapplication.ui.theme.bimserColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tayyib.myapplication.components.ButtonComponent
import com.tayyib.myapplication.components.TextFieldComponent
import com.tayyib.myapplication.models.forgotpassword.ForgotPasswordResponse
import com.tayyib.myapplication.screen.Screen
import com.tayyib.myapplication.util.Resource
import com.tayyib.myapplication.viewmodel.ForgotPasswordViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ForgotPasswordScreen(navController: NavController)
{

    val viewModel : ForgotPasswordViewModel = hiltViewModel()
    val forgotPasswordStatus by viewModel.forgotPasswordStatus.observeAsState()

    val emailState = remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }

    LaunchedEffect(forgotPasswordStatus) {
        when(forgotPasswordStatus)
        {
            is Resource.Error -> {

                Toast.makeText(navController.context, (forgotPasswordStatus as Resource.Error<ForgotPasswordResponse>).message, Toast.LENGTH_SHORT).show()
            }
            is Resource.Loading -> {

            }
            is Resource.Success -> {
                val response =   (forgotPasswordStatus as Resource.Success<ForgotPasswordResponse>).data
                showDialog.value = true

            }

            null -> {

            }
        }
    }


    Scaffold(modifier = Modifier .fillMaxSize(),
        containerColor = MaterialTheme.colors.background,

     topBar = {
        SmallTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colors.background

            ),
            title = {

            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.Login.route)
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Localized description",
                        tint = bimserColor,
                        modifier = Modifier.size(24.dp)

                    )
                }
            },
        )
    }

    )
    {
        Column(modifier = Modifier .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                modifier = Modifier

                    .fillMaxWidth(),
                text = "Şifremi Unuttum", fontSize = 35.sp, fontWeight = FontWeight.Bold, color = bimserColor, textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp)

                    .fillMaxWidth(),
                text = "Şifrenizi sıfırlamak için lütfen e-posta adresinizi girin", fontSize = 20.sp,textAlign = TextAlign.Center, color = Color(0xFF777777)
                )

            TextFieldComponent(placeholder = "E-posta Adresi", passwordVisible = true, value = emailState.value,onValueChange = {emailState.value = it})

            ButtonComponent(
                onClick = {
                          val email = emailState.value
                    viewModel.forgotPassword(email,"1")

                          },
                buttonText = "Şifre Sıfırla",60.dp, bimserColor,Color.White)

        }





        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = {  },
                title = {
                    Text(text = "Bilgi")
                },
                text = {
                    Text(text = (forgotPasswordStatus as? Resource.Success<ForgotPasswordResponse>)?.data?.message ?: "")
                },
                confirmButton = {
                    androidx.compose.material3.Button(onClick = {
                        showDialog.value = false
                        navController.popBackStack()
                        navController.navigate(Screen.Login.route)
                    }) {
                        Text("Tamam")
                    }
                }
            )
        }
    }

    }





@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    val navController = rememberNavController()
    ForgotPasswordScreen(navController)
}