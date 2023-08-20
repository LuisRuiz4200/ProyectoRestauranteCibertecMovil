package com.example.restaurante.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Tarjeta (
    @PrimaryKey
    var id_tarjeta: Int = 0,
    var id_usuario: Int = 0,
    var numero_tarjeta: String = "",
    var cvv_tarjeta: String = "",
    var fecha_tarjeta: String = "",
    var nombre_tarjeta: String = "",
    var fechareg_direntrega: String = "",
    var estado_direntrega: String = "",
)