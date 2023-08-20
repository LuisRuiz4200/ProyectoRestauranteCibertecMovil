package com.example.restaurante.data.room.entity

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartListTypeConverter {
    @TypeConverter // Serealizar
    fun listToJson(value: List<Cart>) = Gson().toJson(value)
    @TypeConverter // Deserealizar
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Cart>::class.java).toList()
}