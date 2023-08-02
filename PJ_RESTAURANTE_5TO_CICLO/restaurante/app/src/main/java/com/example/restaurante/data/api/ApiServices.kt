package com.example.restaurante.data.api

import com.example.restaurante.data.room.entity.Producto
import retrofit2.http.GET

interface ApiServices {
    @GET("polleria/APIPolleria/productos")
    suspend fun getProductos(): List<Producto>
}