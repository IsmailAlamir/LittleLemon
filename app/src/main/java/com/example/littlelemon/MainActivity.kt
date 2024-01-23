package com.example.littlelemon

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.composable.Navigation
import com.example.littlelemon.preferences.PreferencesManager
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PreferencesManager.init(this)

        setContent {

            LittleLemonTheme {
                val navController = rememberNavController()
                Navigation(navController = navController)

            }

        }
    }

}


