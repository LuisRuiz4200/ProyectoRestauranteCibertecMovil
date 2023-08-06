package com.example.restaurante.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restaurante.data.room.entity.Pedido

@Dao
interface PedidoDao {
    @Query("SELECT * FROM Pedido")
    fun getAll(): MutableList<Pedido>

    @Query("SELECT * FROM Pedido WHERE id_pedido = :id")
    fun getPedido(id: Int): Pedido

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPedido(pedidos: MutableList<Pedido>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pedido: Pedido)

    @Query("DELETE FROM Pedido")
    fun deleteAll()
}