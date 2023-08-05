package com.example.restaurante.data.api

import com.example.restaurante.data.room.entity.Direccion
import com.example.restaurante.data.room.entity.Producto
import com.example.restaurante.data.room.entity.Tarjeta
import com.example.restaurante.data.room.entity.Usuario
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiServices {
    // Producto
    @GET("polleria/APIPolleria/getProductos")
    suspend fun getProductos(): List<Producto>
    @GET("polleria/APIPolleria/getProductosById")
    suspend fun getProductosByID(@Query("id") id: Int): Producto

    // Direccion
    @GET("polleria/APIPolleria/getDirecciones")
    suspend fun getDirecciones(): List<Direccion>
    @POST("polleria/APIPolleria/saveDireccion")
    suspend fun saveDireccion(@Body direccion: Direccion) : String
    @PUT("polleria/APIPolleria/updateDireccion")
    suspend fun updateDireccion(@Body direccion: Direccion) : String

    // Tarjeta
    @GET("polleria/APIPolleria/getTarjetas")
    suspend fun getTarjetas(): List<Tarjeta>
    @POST("polleria/APIPolleria/saveTarjeta")
    suspend fun saveTarjeta(@Body tarjeta: Tarjeta) : String
    @PUT("polleria/APIPolleria/updateTarjeta")
    suspend fun updateTarjeta(@Body tarjeta: Tarjeta) : String

    // Pedido



}