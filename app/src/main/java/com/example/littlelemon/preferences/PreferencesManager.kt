package com.example.littlelemon.preferences

import android.content.Context
import android.content.SharedPreferences

object PreferencesManager {
    private const val PREFERENCE_NAME = "LittleLemon"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }



}
