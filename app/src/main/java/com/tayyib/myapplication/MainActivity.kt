package com.tayyib.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tayyib.myapplication.navigation.SetupNavGraph
import com.tayyib.myapplication.preference.EncryptedPreferencesManager
import com.tayyib.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    private lateinit var preferenceManager: EncryptedPreferencesManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferenceManager = EncryptedPreferencesManager(this)

        enableEdgeToEdge()
        setContent {

            MyApplicationTheme{

                navController = rememberNavController()
                SetupNavGraph(navController = navController)

            }

        }
    }


    override fun onDestroy() {
        super.onDestroy()

        preferenceManager.clearPreferences()

        println("silinmedii ${preferenceManager.getToken()}")

    }
}



