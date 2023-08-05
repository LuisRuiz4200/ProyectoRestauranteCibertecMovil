package com.example.restaurante.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Direccion(
    @PrimaryKey
    var id_direntrega: Int = 0,
    var id_usuario: Int = 0,
    var id_distrito: Int = 0,
    var nombre_direntrega: String ="",
    var des_direntrega: String = "",
    var detalle_direntrega: String = "",
    var fechaReg_direntrega: String = "",
    var estado_direntrega: String = ""
)
