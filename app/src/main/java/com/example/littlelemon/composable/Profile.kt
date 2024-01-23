package com.example.littlelemon.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.littlelemon.R
import com.example.littlelemon.preferences.PreferencesManager
import com.example.littlelemon.ui.theme.LittleLemonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavController){

    val firstName by remember { mutableStateOf(PreferencesManager.getString("firstName", "")) }
    val lastName by remember { mutableStateOf(PreferencesManager.getString("lastName", "")) }
    val emailAddress by remember { mutableStateOf(PreferencesManager.getString("emailAddress", "")) }



    Column (modifier = Modifier
        .fillMaxSize()
        .fillMaxWidth(1f)
        .fillMaxHeight(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.littlelemonlogo),
            contentDescription = "Little Lemon",
            modifier = Modifier.fillMaxWidth(0.4f).padding(0.dp, 12.dp,0.dp, 25.dp)
        )


        Text(
            text = "Personal Information",
            modifier= Modifier
                .fillMaxWidth(1f)
                .padding(25.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleMedium
        )

        TextField(

            label = { Text("First Name") },
            value = firstName,
            onValueChange = {},
            enabled = false,
            modifier= Modifier
                .fillMaxWidth(1f)
                .padding(25.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = LittleLemonColor.cloud),
            shape= RoundedCornerShape(8.dp),
        )

        TextField(
            label = { Text("Last Name") },
            value = lastName,
            onValueChange = {},
            enabled = false,
            modifier= Modifier
                .fillMaxWidth(1f)
                .padding(25.dp, 0.dp, 25.dp, 25.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = LittleLemonColor.cloud),
            shape= RoundedCornerShape(8.dp),

            )

        TextField(
            label = { Text("Email Address") },
            value = emailAddress,
            onValueChange = {},
            enabled = false,
            modifier= Modifier
                .fillMaxWidth(1f)
                .padding(25.dp, 0.dp, 25.dp, 25.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = LittleLemonColor.cloud),
            shape= RoundedCornerShape(8.dp),
        )


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.yellow),
            shape= RoundedCornerShape(16.dp),
            onClick = {
                PreferencesManager.clear()
                navController.navigate("OnBoarding")

            },

        ) {

            Text(text = "Log Out")


        }




    }
}
//
//@Composable
//@Preview(showBackground = true)
//fun ProfilePreview(){
////    Profile(navController: NavController)
//}