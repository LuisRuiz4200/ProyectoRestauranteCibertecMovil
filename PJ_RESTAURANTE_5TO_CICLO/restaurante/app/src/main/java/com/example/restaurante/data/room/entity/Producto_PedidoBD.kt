package com.example.restaurante.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Producto_PedidoBD(
    @PrimaryKey(autoGenerate = true)
    var id_producto_pedido:Int  =0,
    var id_pedido:Int = 0,
    var id_producto:Int = 0,
    var cantidad_producto: Int = 0,
)