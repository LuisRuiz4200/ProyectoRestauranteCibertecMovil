package com.example.restaurante.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class CompraBD(
    @PrimaryKey(autoGenerate = true)
    var id_compra:Int = 0,
    var id_pedido:Int = 0,
    var id_medioPago:Int = 0,
    var monto_compra:Double = 0.0,
    var fechaReg_compra:Date? = null,
    var estado_compra:String = ""
)
