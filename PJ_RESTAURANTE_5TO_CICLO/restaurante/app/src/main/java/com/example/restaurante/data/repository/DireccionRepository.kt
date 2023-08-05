package com.example.restaurante.data.repository

import android.app.Application
import com.example.restaurante.data.room.entity.Direccion

class DireccionRepository (application: Application) : BaseRepository(application){

    suspend fun getDirecciones() = apiClient.getDirecciones()
    suspend fun saveDireccion(direccion: Direccion) = apiClient.saveDireccion(direccion)
    suspend fun updateDireccion(direccion: Direccion) = apiClient.updateDireccion(direccion)
}