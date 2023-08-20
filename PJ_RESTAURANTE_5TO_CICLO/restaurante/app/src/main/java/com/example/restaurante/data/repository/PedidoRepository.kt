package com.example.restaurante.data.repository

import android.app.Application
import com.example.restaurante.data.room.entity.Pedido

class PedidoRepository (application: Application) : BaseRepository(application){
    suspend fun savePedido(pedido: Pedido) = apiClient.savePedido(pedido)
    suspend fun getPedidoByUser(id: Int) = apiClient.getPedidoByUser(id)
}