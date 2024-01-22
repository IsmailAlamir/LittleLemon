package com.example.littlelemon

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun MenuCategoryImplement(categories: List<String>){
    Row(
        modifier = Modifier
            .wrapContentWidth(Alignment.Start)
            .padding(12.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach {
            MenuCategory(category = it)
        }
    }

}

@Composable
fun MenuCategory(category : String){

    Button(onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(containerColor  = Color.LightGray),
        shape = RoundedCornerShape(40.dp),
    ) {
        Text(text = category)
    }

}



@Preview(showBackground = true)
@Composable
fun MenuCategoryImplementPreview() {
    MenuCategoryImplement(Categories)
}


val Categories= listOf(
    "Lunch",
    "Desert",
    "Dinner",
    "Cake",
    "Mains"

)