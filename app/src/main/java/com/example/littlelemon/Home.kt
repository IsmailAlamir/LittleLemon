package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Home(navController: NavController){
    val context= LocalContext.current
    val database by lazy {
        DatabaseSingleton.getDatabase(context)
    }


    Column (modifier = Modifier
        .fillMaxSize()
        .fillMaxWidth(1f)
        .fillMaxHeight(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            Image(
                painter = painterResource(id = R.drawable.littlelemonlogo),
                contentDescription = "Little Lemon",
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .padding(0.dp, 12.dp, 0.dp, 25.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.littlelemonlogo),
                contentDescription = "Little Lemon",
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .padding(0.dp, 12.dp, 0.dp, 25.dp)
                    .clickable {
                        navController.navigate("profile")
                    }
            )
        }

        HeroSection()
        MenuCategoryImplement(Categories)
        val context = LocalContext.current
        val database = DatabaseSingleton.getDatabase(context)
        val menuItemDao = database.menuItemDao()

        MenuItems(menuItems = menuItemDao.getAll())

    }



}









//@Composable
//@Preview
//fun HomePreview(){
//    Home()
//}