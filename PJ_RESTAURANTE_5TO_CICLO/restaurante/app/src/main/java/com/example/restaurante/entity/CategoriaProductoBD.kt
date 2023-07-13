package com.example.restaurante.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoriaProductoBD(
    @PrimaryKey(autoGenerate = true)
    var id_categoria_producto:Int = 0,
    var des_categoria_producto:String = ""
)
