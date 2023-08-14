package com.example.restaurante.data.api

import com.example.restaurante.data.room.entity.Direccion
import com.example.restaurante.data.room.entity.Pedido
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
    @GET("api/Producto/getProductos")
    suspend fun getProductos(): List<Producto>
    @GET("api/Producto/getProductosById")
    suspend fun getProductosByID(@Query("id") id: Int): Producto

    // Direccion
    @GET("api/Direccion/getDireccion")
    suspend fun getDirecciones(@Query("id_usuario") id: Int): List<Direccion>
    @POST("api/Direccion/saveDireccion")
    suspend fun saveDireccion(@Body direccion: Direccion) : String
    @PUT("api/Direccion/updateDireccion")
    suspend fun updateDireccion(@Body direccion: Direccion) : String

    // Tarjeta
    @GET("api/Tarjeta/getTarjeta")
    suspend fun getTarjetas(@Query("id_usuario")id: Int): List<Tarjeta>
    @POST("api/Tarjeta/saveTarjeta")
    suspend fun saveTarjeta(@Body tarjeta: Tarjeta) : String
    @PUT("api/Tarjeta/updateTarjeta")
    suspend fun updateTarjeta(@Body tarjeta: Tarjeta) : String

    // Pedido
    @POST("api/Pedido/SavePedido")
    suspend fun savePedido(@Body pedido: Pedido) : String


}