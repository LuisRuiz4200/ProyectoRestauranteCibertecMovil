package com.example.restaurante.data.api

import com.example.restaurante.data.room.entity.Producto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("polleria/APIPolleria/productos")
    suspend fun getProductos(): List<Producto>

    @GET("polleria/APIPolleria/productosbyid")
    suspend fun getProductosByID(
        @Query("id")
        id: Int
    ): Producto
}