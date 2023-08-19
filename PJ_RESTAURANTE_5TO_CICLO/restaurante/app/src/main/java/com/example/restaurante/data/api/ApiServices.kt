package com.example.restaurante.data.api

import com.example.restaurante.data.room.entity.Categoria
import com.example.restaurante.data.room.entity.Direccion
import com.example.restaurante.data.room.entity.Favorito
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
    // Usuario
    @POST("api/Usuario/loginUsuario")
    suspend fun loginUsuario(@Body usuario: Usuario): Usuario

    @POST("api/Usuario/saveUsuario")
    suspend fun saveUsuario(@Body usuario: Usuario): String

    @PUT("api/Usuario/updateUsuario")
    suspend fun updateUsuario(@Body usuario: Usuario): String

    @PUT("api/Usuario/changePass")
    suspend fun changePass(@Body usuario: Usuario): String

    @POST("api/Usuario/validUsuario")
    suspend fun validUsuario(@Body usuario: Usuario): String

    // Producto
    @GET("api/Producto/getProductos")
    suspend fun getProductos(): List<Producto>
    @GET("api/Producto/getProductosById")
    suspend fun getProductosByID(@Query("id") id: Int): Producto
    @GET("api/Producto/getProductosbycategoria")
    suspend fun getProductosByCategoria(@Query("id_categoria_producto") id: Int): List<Producto>
    @GET("api/Producto/getProductosbynombre")
    suspend fun getProductosByNombre(@Query("nombre") nombre: String): List<Producto>

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
    @PUT("api/Tarjeta/deleteTarjeta")
    suspend fun deleteTarjeta(@Body tarjeta: Tarjeta) : String

    // Pedido
    @POST("api/Pedido/SavePedido")
    suspend fun savePedido(@Body pedido: Pedido) : String
    @GET("api/Pedido/getPedidoByUser")
    suspend fun getPedidoByUser(@Query("id") id: Int) : List<Pedido>

    // Categoria
    @GET("api/Categoria/getCategoria")
    suspend fun getCategorias() : List<Categoria>

    // Favorito
    @GET("api/Favorito/getFavorito")
    suspend fun getFavorito(@Query("id_usuario")id: Int): List<Favorito>
    @POST("api/Favorito/saveFavorito")
    suspend fun saveFavorito(@Body favorito: Favorito) : String
    @PUT("api/Favorito/deleteFavorito")
    suspend fun deleteFavorito(@Body favorito: Favorito) : String
}