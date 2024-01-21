package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(navController : NavHostController){

    val startDestination = if (isUserDataStored()){
        Home.route
    } else {
        OnBoarding.route
    }


    NavHost(navController = navController, startDestination = startDestination){

        composable(Home.route) {
            Home()
        }

        composable(OnBoarding.route) {
            Onboarding(navController)
        }

        composable(Profile.route) {
            Profile()
        }


    }





}

fun isUserDataStored(): Boolean {

    val firstName = PreferenceHelper.getString("firstName", "")
    val lastName = PreferenceHelper.getString("lastName", "")
    val emailAddress = PreferenceHelper.getString("emailAddress", "")
    return firstName.isNotEmpty() && lastName.isNotEmpty() && emailAddress.isNotEmpty()

}
