package com.example.restaurante.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restaurante.data.room.entity.Cart

@Dao
interface CartDao {
    @Query ("SELECT * FROM Cart")
    fun getAll() : MutableList<Cart>

    @Query("SELECT * FROM Cart where id_producto_pedido= :id")
    fun getCartById(id:Int): Cart

    @Query("SELECT * FROM Cart where id_producto= :id")
    fun getCartByIdProducto(id:Int): Cart

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertCart(producto: Cart)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(carts: MutableList<Cart>)

    @Query("DELETE FROM Cart")
    fun deleteAll()

    @Query("DELETE FROM Cart where id_producto = :id")
    fun deleteProducto(id:Int)
}