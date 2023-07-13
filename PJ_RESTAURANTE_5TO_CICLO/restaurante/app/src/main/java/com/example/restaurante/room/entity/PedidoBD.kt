package com.example.restaurante.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.time.DateTimeException
import java.util.Date

@Entity
data class PedidoBD(
    @PrimaryKey(autoGenerate = true)
    var id_pedido:Int = 0,
    var id_usuario_cliente:Int = 0,
    var id_dirEntrega:Int = 0,
    var id_colaborador_repartidor:Int = 0,
    var tiempoEntrega_pedido :Time? = null,
    var fechaReg_pedido : Date? = null,
    var fechaAct_pedido : Date? = null,
    var estado_pedido:String = ""

)
