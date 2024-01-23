package com.example.littlelemon.composable

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.navigation.Home
import com.example.littlelemon.navigation.OnBoarding
import com.example.littlelemon.navigation.Profile
import com.example.littlelemon.preferences.PreferencesManager

@Composable
fun Navigation(navController : NavHostController){

    val startDestination = if (isUserDataStored()){
        Home.route
    } else {
        OnBoarding.route
    }


    NavHost(navController = navController, startDestination = startDestination){

        composable(Home.route) {
            Home(navController)
        }

        composable(OnBoarding.route) {
            Onboarding(navController)
        }

        composable(Profile.route) {
            Profile(navController)
        }


    }




}

fun isUserDataStored(): Boolean {

    val firstName = PreferencesManager.getString("firstName", "")
    val lastName = PreferencesManager.getString("lastName", "")
    val emailAddress = PreferencesManager.getString("emailAddress", "")
    return firstName.isNotEmpty() && lastName.isNotEmpty() && emailAddress.isNotEmpty()

}
