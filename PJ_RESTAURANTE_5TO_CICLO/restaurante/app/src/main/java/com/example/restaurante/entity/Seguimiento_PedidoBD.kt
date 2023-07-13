package com.example.restaurante.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Seguimiento_PedidoBD(
    @PrimaryKey
    var id_pedido:Int = 0,
    @PrimaryKey(autoGenerate = true)
    var id_seguimiento_pedido:Int = 0,
    var fechaReg_seguimiento_pedido:Date?=null,
    var estado_seguimiento_pedido:String=""
)
