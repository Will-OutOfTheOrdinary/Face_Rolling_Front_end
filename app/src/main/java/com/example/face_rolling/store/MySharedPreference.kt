package com.example.face_rolling.store

import android.content.Context
import android.content.SharedPreferences
import com.example.face_rolling.data.User

class SharedPreferencesHelper private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences = context.applicationContext.getSharedPreferences(
        "MySharedPreferences",
        Context.MODE_PRIVATE
    )

    companion object {
        @Volatile
        private var instance: SharedPreferencesHelper? = null

        fun getInstance(context: Context): SharedPreferencesHelper {
            return instance ?: synchronized(this) {
                instance ?: SharedPreferencesHelper(context).also { instance = it }
            }
        }
    }

    fun saveData(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getData(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

}
