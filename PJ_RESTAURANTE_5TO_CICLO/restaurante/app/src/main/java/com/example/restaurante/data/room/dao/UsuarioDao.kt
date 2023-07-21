package com.example.restaurante.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restaurante.data.room.entity.Usuario

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM Usuario")
    fun getAll():MutableList<Usuario>

    @Query("SELECT * FROM Usuario where id_usuario= :id")
    fun getUsuarioById(id:Int): Usuario

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertUsuario(usuario: Usuario)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(usuarios: MutableList<Usuario>)

    @Query("DELETE FROM Usuario")
    fun deleteAll()

    @Query("DELETE FROM Usuario where id_usuario = :id")
    fun deleteUsuario(id:Int)
}