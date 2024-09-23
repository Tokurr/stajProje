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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.tayyib.myapplication.R
import com.tayyib.myapplication.screen.Screen
import com.tayyib.myapplication.components.ButtonComponent
import com.tayyib.myapplication.components.TextFieldComponent
import com.tayyib.myapplication.ui.theme.bimserColor
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tayyib.myapplication.models.login.AuthResponse
import com.tayyib.myapplication.preference.EncryptedPreferencesManager
import com.tayyib.myapplication.util.Resource
import com.tayyib.myapplication.viewmodel.LoginViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController) {

    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val context = LocalContext.current
    val viewModel: LoginViewModel = hiltViewModel()
    val loginStatus by viewModel.loginStatus.observeAsState()
    val isLoading = remember { mutableStateOf(false) }
    val pref = EncryptedPreferencesManager(context)


    Scaffold(
        modifier = Modifier.fillMaxSize()

    )

    {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {


            if (!isLoading.value) {
                Text(
                    modifier = Modifier.padding(top = 130.dp),
                    text = "Bimser",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = bimserColor
                )
                Text(
                    modifier = Modifier.padding(top = 55.dp),
                    text = "Hoşgeldiniz!",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = bimserColor
                )
                Text(
                    text = "Hesabınıza giriş yapın",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = bimserColor
                )

                Spacer(modifier = Modifier.height(20.dp))
                TextFieldComponent(
                    placeholder = "E-posta adresi",
                    true,
                    R.drawable.ic_mail,
                    value = emailState.value,
                    onValueChange = { emailState.value = it })
                TextFieldComponent(
                    placeholder = "Şifre",
                    false,
                    R.drawable.eye,
                    value = passwordState.value,
                    onValueChange = { passwordState.value = it })
                Spacer(modifier = Modifier.height(10.dp))


                Spacer(modifier = Modifier.height(30.dp))
                ButtonComponent(
                    onClick = {
                        val email = emailState.value
                        val password = passwordState.value


                        if (email.isNotBlank() && password.isNotBlank()) {

                            viewModel.getTokenAndLogin(email, password)

                        } else {
                            Toast.makeText(
                                context,
                                "Lütfen tüm alanları doldurun",
                                Toast.LENGTH_SHORT
                            ).show()
                        }


                    },
                    buttonText = "GİRİŞ",
                    height = 60.dp,
                    bimserColor,
                    Color.White
                )

                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Şifremi Unuttum ?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = bimserColor,
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                            navController.navigate(route = Screen.ForgotPassword.route)

                        }
                )

            }


            if (isLoading.value) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center

                ) {
                    CircularProgressIndicator(
                        color = bimserColor
                    )
                }
            }


        }
        LaunchedEffect(loginStatus) {
            when (loginStatus) {

                is Resource.Loading -> {

                    isLoading.value = true
                }

                is Resource.Success -> {

                    (loginStatus as Resource.Success<AuthResponse>).data?.let { result ->

                        println(result.tokenResponse.token)
                        print(result.loginResponse.id.toString())

                        pref.saveTokenAndUserID(
                            result.tokenResponse.token,
                            result.loginResponse.id.toString()
                        )

                    }

                    navController.popBackStack()
                    navController.navigate(Screen.Home.route)
                    isLoading.value = false
                }

                is Resource.Error -> {
                    isLoading.value = false
                    Toast.makeText(
                        context,
                        (loginStatus as Resource.Error<AuthResponse>).message ?: "Login failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {}
            }
        }


    }


}


@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}