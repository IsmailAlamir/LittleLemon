package com.example.littlelemon.navigation

interface Destinations {
    val route: String
}

object Home: Destinations {
    override val route = "Home"
}

object Profile: Destinations {
    override val route= "Profile"
}

object OnBoarding: Destinations {
    override val route= "OnBoarding"
}