package com.example.restaurante.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.Date

@Entity
data class Pedido(
    @PrimaryKey(autoGenerate = true)
    var id_pedido: Int = 0,
    var id_usuario_cliente: Int = 0,
    var id_dirEntrega: Int = 0,
    var id_colaborador_repartidor: Int = 0,
    var tiempoEntrega_pedido : String = "",
    var fechaReg_pedido : String = "",
    var fechaAct_pedido : String = "",
    var estado_pedido: String = ""

)
