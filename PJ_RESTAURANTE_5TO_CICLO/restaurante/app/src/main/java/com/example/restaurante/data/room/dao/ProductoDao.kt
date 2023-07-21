package com.example.restaurante.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restaurante.data.room.entity.Producto

@Dao
interface ProductoDao {
    @Query("SELECT * FROM Producto")
    fun getAll():MutableList<Producto>

    @Query("SELECT * FROM Producto where id_producto= :id")
    fun getProductoById(id:Int): Producto

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertProducto(producto: Producto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(productos: MutableList<Producto>)

    @Query("DELETE FROM Producto")
    fun deleteAll()

    @Query("DELETE FROM Producto where id_producto = :id")
    fun deleteProducto(id:Int)
}