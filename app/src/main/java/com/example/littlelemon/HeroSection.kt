package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun HeroSection(){
    Column(
        modifier = Modifier
            .background(LittleLemonColor.green)
            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = "Little Lemon",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = LittleLemonColor.yellow
        )
        Text(
            text = "Amman",
            fontSize = 24.sp,
            color = LittleLemonColor.cloud
        )
        Row (
            modifier = Modifier
                .padding(top = 18.dp)
        ){

            Text(text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                color = LittleLemonColor.cloud ,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(bottom = 28.dp)
                    .fillMaxWidth(0.6f))

            Image(
                painter = painterResource(id = R.drawable.upperpanelimage),
                contentDescription = "Upper Panel Image",
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))

            )


        }


        Button(onClick = {

        },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(LittleLemonColor.yellow)
        )
        {
            Text(
                text = "Reserve a table",
                fontSize=18.sp,
                fontWeight = FontWeight.Bold,
                color = LittleLemonColor.charcoal

            )

        }

    }}


