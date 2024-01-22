package com.example.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.ui.theme.LittleLemonColor.cloud
import com.example.littlelemon.ui.theme.LittleLemonColor.gray
import com.example.littlelemon.ui.theme.Typography


@Composable
fun MenuItems(menuItems: LiveData<List<MenuItemRoom>>){

    Column {
        WeeklySpecial()
        LazyColumn {
            itemsIndexed(menuItems.value ?: emptyList()) { _, dish ->
                MenuDish( dish)
            }
        }
    }

}



@Composable
fun WeeklySpecial(){
    Card (

        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White),

        ){
        Text(
            text = "Weekly Special",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
        )
    }

}


@Composable
fun DishesList(dishes: List<MenuItemRoom>) {
    LazyColumn {
        items(dishes) { dish ->
            MenuDish(dish = dish)
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun MenuDish( dish: MenuItemRoom) {
    Card(
//        onClick = {
//            Log.d("AAA", "Click ${dish.id}")
//            navController?.navigate(DishDetails.route + "/${dish.id}")
//        },
        colors = CardDefaults.cardColors(containerColor = cloud),

        ){
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        ){
            Column {
                Text(
                    text = dish.title,
                    style = Typography.titleMedium
                )
                Text(text = dish.description,
                    style = Typography.bodyLarge,
                    color = gray,
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(.75f)
                )
                Text(text = "$"+ dish.price,
                    color = gray,
                    style = Typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            GlideImage(
                modifier= Modifier.size(85.dp),
                contentScale = ContentScale.FillHeight,
                model = dish.image,
                contentDescription = dish.title+ " Image"
                )

        }


    }
    Divider(modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        color = LittleLemonColor.yellow,
        thickness = 1.dp
    )

}


