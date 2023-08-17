package com.example.restaurante.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Categoria(
    @PrimaryKey(autoGenerate = true)
    var id_categoria_producto:Int = 0,
    var des_categoria_producto:String = ""
)
