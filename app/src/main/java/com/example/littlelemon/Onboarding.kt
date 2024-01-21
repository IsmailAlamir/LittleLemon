package com.example.littlelemon

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.LittleLemonColor.cloud
import com.example.littlelemon.ui.theme.LittleLemonColor.green
import com.example.littlelemon.ui.theme.LittleLemonColor.yellow



@SuppressLint("CommitPrefEdits")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding(navController: NavController){
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var emailAddress by remember { mutableStateOf("") }
    var registrationStatus by remember { mutableStateOf<String?>(null) }


    Column (modifier = Modifier
        .fillMaxSize()
        .fillMaxWidth(1f)
        .fillMaxHeight(1f),
        horizontalAlignment =CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.littlelemonlogo),
            contentDescription ="Little Lemon",
            modifier= Modifier.fillMaxWidth(0.4f)
        )


        Text(
            text = "Let's get to know you",
            modifier= Modifier
                .fillMaxWidth(1f)
                .background(green)
                .padding(vertical = 25.dp),
            textAlign = TextAlign.Center,
            color= cloud,
            style = MaterialTheme.typography.titleLarge

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
            value = "Ismail",
            onValueChange =  { firstName = it },
            modifier= Modifier
                .fillMaxWidth(1f)
                .padding(25.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = cloud),
            shape= RoundedCornerShape(8.dp),
            )

        TextField(
            label = { Text("Last Name") },
            value = "Al Amir",
            onValueChange =  { lastName = it },
            modifier= Modifier
                .fillMaxWidth(1f)
                .padding(25.dp, 0.dp, 25.dp, 25.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = cloud),
            shape= RoundedCornerShape(8.dp),

        )

        TextField(
            label = { Text("Email Address") },
            value = "name@example.com",
            onValueChange =  { emailAddress = it },
            modifier= Modifier
                .fillMaxWidth(1f)
                .padding(25.dp, 0.dp, 25.dp, 25.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = cloud),
            shape= RoundedCornerShape(8.dp),
            )

        Button(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = yellow),
            shape= RoundedCornerShape(16.dp),
            onClick = {
                if (firstName.isBlank() || lastName.isBlank() || emailAddress.isBlank()) {
                    registrationStatus = "Registration unsuccessful. Please enter all data."
                } else {
                    // Save data to SharedPreferences
                    PreferenceHelper.putString("firstName", firstName)
                    PreferenceHelper.putString("lastName", lastName)
                    PreferenceHelper.putString("emailAddress", emailAddress)

                    // Update registration status
                    registrationStatus = "Registration successful!"

                    // Navigate to Home screen
                    navController.navigate("home")
                }
            }
        ) {
            Text(text = "Register")
            
        }
    }


}




