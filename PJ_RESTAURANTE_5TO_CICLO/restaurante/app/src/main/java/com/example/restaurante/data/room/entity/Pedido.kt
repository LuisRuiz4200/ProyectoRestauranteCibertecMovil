package com.example.restaurante.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
@TypeConverters(CartListTypeConverter::class)
data class Pedido(
    @PrimaryKey(autoGenerate = true)
    var id_pedido: Int = 0,
    var id_usuario_cliente: Int = 0,
    var id_dirEntrega: Int = 0,
    var id_colaborador_repartidor: Int = 0,
    var tiempoEntrega_pedido : String = "",
    var id_tarjeta: Int = 0,
    var id_medio_pago: Int = 0,
    var monto_compra: Double = 0.0,
    var carts: List<Cart> = emptyList(),
    var fechaReg_pedido : String = "",
    var fechaAct_pedido : String = "",
    var estado_pedido: String = "",
    var nombre_direntrega: String = "",
    var des_direntrega: String = "",
    var des_medio_pago: String ="",
)
