package com.example.littlelemon.helpers

import com.example.littlelemon.data.AppDatabase
import com.example.littlelemon.data.MenuItemNetwork
import com.example.littlelemon.data.MenuNetworkData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json

suspend fun fetchMenu(url:String): List<MenuItemNetwork> {

    val httpClient = HttpClient(Android){
        install(ContentNegotiation){
            json(contentType = ContentType("text", "plain"))
        }
    }

    val menuNetwork = httpClient.get(url).body<MenuNetworkData>()
    return menuNetwork.menu
}

fun saveMenuToDatabase(database: AppDatabase, menuItemsNetwork: List<MenuItemNetwork>) {
    val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
    database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
}
