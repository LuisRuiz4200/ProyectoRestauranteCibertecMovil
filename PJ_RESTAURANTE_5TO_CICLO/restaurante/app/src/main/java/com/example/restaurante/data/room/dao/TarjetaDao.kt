package com.example.restaurante.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restaurante.data.room.entity.Tarjeta

@Dao
interface TarjetaDao {
    @Query("SELECT * FROM Tarjeta WHERE id_usuario = :id")
    fun getTarjetas(id: Int) : MutableList<Tarjeta>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTarjeta(tarjeta: Tarjeta)

    @Query("DELETE FROM Tarjeta WHERE id_tarjeta = :id")
    fun deleteTarjetaById(id: Int)
}