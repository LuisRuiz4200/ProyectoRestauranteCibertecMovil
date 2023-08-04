package com.example.restaurante.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Cart(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id_producto_pedido")
    var id_cart : Int  = 0,
    var id_pedido : Int = 0,
    var id_producto : Int = 0,
    var cantidad_producto : Int = 0,
    var nom_producto : String="",
    var des_producto : String ="",
    var preciouni_producto: Double = 0.0,
    var imagen_producto: String = ""
)
