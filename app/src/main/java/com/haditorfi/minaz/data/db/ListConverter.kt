package com.haditorfi.minaz.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.haditorfi.minaz.data.service.service.Service

class ListConverter {
    @TypeConverter
    fun listToJsonString(value: List<Service>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) =
        Gson().fromJson(value, Array<Service>::class.java).toList()
}