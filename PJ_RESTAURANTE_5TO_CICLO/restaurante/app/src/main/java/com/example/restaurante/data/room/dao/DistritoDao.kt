package com.example.restaurante.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.restaurante.data.room.entity.Distrito

@Dao
interface DistritoDao {
    @Query("SELECT * FROM Distrito")
    fun getAll() : MutableList<Distrito>

    @Query("SELECT * FROM Distrito WHERE id_distrito = :id")
    fun getById(id: Int) : Distrito

    @Query("SELECT * FROM Distrito WHERE des_distrito = :nombre")
    fun getByName(nombre: String) : Distrito
}