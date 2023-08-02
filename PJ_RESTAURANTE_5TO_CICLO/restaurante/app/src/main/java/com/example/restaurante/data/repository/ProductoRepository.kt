package com.example.restaurante.data.repository

import android.app.Application

class ProductoRepository (application: Application) : BaseRepository(application){

    suspend fun getProductos() = apiClient.getProductos()

}