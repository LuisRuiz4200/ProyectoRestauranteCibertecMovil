package com.example.restaurante.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Favorito (
    @PrimaryKey(autoGenerate = true)
    var id_favorito : Int  = 0,
    var id_usuario : Int = 0,
    var id_producto : Int = 0,
    var producto : Producto = Producto()
)