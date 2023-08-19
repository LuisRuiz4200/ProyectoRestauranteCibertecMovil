package com.example.restaurante.data.room.entity

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartListTypeConverter {
//    @TypeConverter
//    fun fromCartList(value: List<Cart>): String {
//        return Gson().toJson(value)
//    }
//    @TypeConverter
//    fun toCartList(value: String) = Gson().fromJson(value, Array<Cart>::class.java).toList()

    @TypeConverter
    fun listToJson(value: List<Cart>) = Gson().toJson(value)
    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Cart>::class.java).toList()
//
//    private val gson = Gson()
//
//    @TypeConverter
//    fun fromList(list: List<Cart>): String {
//        return gson.toJson(list)
//    }
//
//    @TypeConverter
//    fun toList(data: String): List<Cart> {
//        val listType = object : TypeToken<List<Cart>>() {}.type
//        return gson.fromJson(data, listType)
//    }
}