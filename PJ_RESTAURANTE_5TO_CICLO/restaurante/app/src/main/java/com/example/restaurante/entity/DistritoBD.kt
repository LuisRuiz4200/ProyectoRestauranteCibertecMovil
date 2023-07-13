package com.example.restaurante.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DistritoBD (
    @PrimaryKey(autoGenerate = true)
    var id_distrito:Int = 0,
    var des_distrito:String = ""
)