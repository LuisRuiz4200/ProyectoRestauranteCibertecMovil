package com.example.restaurante.data.repository

import android.app.Application

class CategoriaRepository (application: Application) : BaseRepository(application) {
    suspend fun getCategorias() = apiClient.getCategorias()
}