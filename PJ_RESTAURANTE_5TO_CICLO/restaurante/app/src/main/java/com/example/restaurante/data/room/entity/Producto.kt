package com.example.restaurante.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Producto(
    @PrimaryKey(autoGenerate = true)
    var id_producto: Int=0,
    var id_categoria_producto : Int= 0,
    var nom_producto : String="",
    var des_producto : String ="",
    var preciouni_producto: Double = 0.0,
    var stock_producto: Int=0,
    var imagen_producto: String = ""
)
