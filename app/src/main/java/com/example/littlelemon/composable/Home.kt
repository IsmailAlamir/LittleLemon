package com.example.littlelemon.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.data.MenuViewModel
import com.example.littlelemon.R
import com.example.littlelemon.data.MenuItemRoom
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.ui.theme.LittleLemonColor.green
import com.example.littlelemon.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController){

    var searchPhrase by remember {mutableStateOf("")}
    var selectedCategories by remember { mutableStateOf(emptyList<String>()) }

    Column (modifier = Modifier
        .fillMaxSize()
        .fillMaxWidth(1f)
        .fillMaxHeight(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UpperBar(navController)



//      ____________________________  Hero Section  ____________________________
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
//            Button(onClick = {
//            },
//                shape = RoundedCornerShape(20.dp),
//                colors = ButtonDefaults.buttonColors(LittleLemonColor.yellow)
//            )
//            {
//            Text(
//                text = "Reserve a table",
//                fontSize=18.sp,
//                fontWeight = FontWeight.Bold,
//                color = LittleLemonColor.charcoal
//            )
//
//            }

            TextField(
                placeholder = {Text("Enter search phrase") },
                label = { Text("Search") },
                value = searchPhrase,
                onValueChange =  { searchPhrase = it },
                modifier= Modifier
                    .fillMaxWidth(1f)
                    .padding(0.dp, 12.dp, 0.dp, 0.dp),
                colors = TextFieldDefaults.textFieldColors(containerColor = LittleLemonColor.cloud),
                shape= RoundedCornerShape(8.dp),
                leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") }


            )
        }

        //      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^  Hero Section  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^





        val viewModel: MenuViewModel = viewModel()
        val databaseMenuItems = viewModel.getAllDatabaseMenuItems().observeAsState(emptyList()).value

        LaunchedEffect(Unit) {
            viewModel.fetchMenuDataIfNeeded()
        }


        val categorise = databaseMenuItems.map { it.category }.distinct()

        MenuCategoryImplement(categories = categorise) { category ->
            selectedCategories = if (selectedCategories.contains(category)) {
                selectedCategories - category
            } else {
                selectedCategories + category
            }
        }


        MenuItems(menuItems = databaseMenuItems,selectedCategories = selectedCategories ,searchPhrase=searchPhrase)

    }
}




@Composable
fun UpperBar(navController: NavController) {
    Row(
        modifier= Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
        ) {

        Spacer(modifier = Modifier.width(50.dp))

        Image(
            painter = painterResource(id = R.drawable.littlelemonlogo),
            contentDescription = "Little Lemon",
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(0.dp, 8.dp, 0.dp, 8.dp)
        )

        Box(modifier = Modifier
            .size(40.dp)
            .clickable { navController.navigate("profile") }){
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile",
                tint = green,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }

}




@Composable
fun MenuCategoryImplement(categories: List<String>, onCategorySelected: (String) -> Unit) {

    Row(
        modifier = Modifier
            .wrapContentWidth(Alignment.Start)
            .padding(12.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach {
            MenuCategory(category = it, onCategorySelected = onCategorySelected)
        }
    }

}

@Composable
fun MenuCategory(category: String, onCategorySelected: (String) -> Unit) {
    var clicked by remember { mutableStateOf(false) }

    Button(onClick = {
        clicked= !clicked
        onCategorySelected(category)
                     },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (clicked) green else Color.LightGray

        ),
        shape = RoundedCornerShape(40.dp),
    ) {
        Text(text = category,
            color = if (clicked) Color.LightGray else green
        )
    }


}



    @Composable
fun MenuItems(menuItems: List<MenuItemRoom>, selectedCategories: List<String>, searchPhrase: String){

        val filteredItems: List<MenuItemRoom> = if (selectedCategories.isEmpty() && searchPhrase=="") {
            menuItems
        } else {
            menuItems.filter { menuItem ->
                selectedCategories.contains(menuItem.category)
                        || menuItem.category==searchPhrase
            }
        }

    Column {
        WeeklySpecial()
        LazyColumn {
            itemsIndexed(filteredItems) { _, dish ->
                MenuDish(dish)

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




@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuDish( dish: MenuItemRoom) {
    Card(
//        onClick = {
//            Log.d("AAA", "Click ${dish.id}")
//            navController?.navigate(DishDetails.route + "/${dish.id}")
//        },
        colors = CardDefaults.cardColors(containerColor = LittleLemonColor.cloud),

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
                    color = LittleLemonColor.gray,
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(.75f)
                )
                Text(text = "$"+ dish.price,
                    color = LittleLemonColor.gray,
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


//@Preview(showBackground = true)
//@Composable
//fun MenuCategoryImplementPreview() {
//    val categories = listOf<String>("1","2")
//    MenuCategoryImplement(categories)
//}



//@Composable
//@Preview
//fun HomePreview(){
//    Home()
//}

