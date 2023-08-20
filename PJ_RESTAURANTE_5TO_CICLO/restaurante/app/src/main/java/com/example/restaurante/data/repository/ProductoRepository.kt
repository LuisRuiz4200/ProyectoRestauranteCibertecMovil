package com.example.restaurante.data.repository

import android.app.Application

class ProductoRepository (application: Application) : BaseRepository(application){

    suspend fun getProductos() = apiClient.getProductos()
    suspend fun getProductosById(idProducto: Int) = apiClient.getProductosByID(idProducto)
    suspend fun getProductosByCategoria(id: Int) = apiClient.getProductosByCategoria(id)
    suspend fun getProductosByNombre(nombre: String) = apiClient.getProductosByNombre(nombre)
}