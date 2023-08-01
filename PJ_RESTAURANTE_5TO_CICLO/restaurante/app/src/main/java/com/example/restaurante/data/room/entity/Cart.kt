package com.example.restaurante.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cart(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_producto_pedido")
    var id_cart : Int  = 0,
    var id_pedido : Int = 0,
    var id_producto : Int = 0,
    var cantidad_producto : Int = 0
)
