package com.example.restaurante.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restaurante.data.room.entity.ProductoBD

@Dao
interface ProductoDao {
    @Query("SELECT * FROM ProductoBD")
    fun getAll():MutableList<ProductoBD>

    @Query("SELECT * FROM ProductoBD where id_producto= :id")
    fun getProductoById(id:Int): ProductoBD

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertProducto(producto: ProductoBD)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductos(productos: ProductoBD)

    @Query("DELETE FROM ProductoBD")
    fun deleteAll()

    @Query("DELETE FROM ProductoBD where id_producto = :id")
    fun deleteProducto(id:Int)
}