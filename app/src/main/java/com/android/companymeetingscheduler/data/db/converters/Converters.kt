package com.phone.app.mms.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()
    private val type = object : TypeToken<List<String>>() {

    }.getType()

    @TypeConverter
    fun stringToList(json: String): List<String> {
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun listToString(string: List<String>): String {
        return gson.toJson(string, type)
    }

}