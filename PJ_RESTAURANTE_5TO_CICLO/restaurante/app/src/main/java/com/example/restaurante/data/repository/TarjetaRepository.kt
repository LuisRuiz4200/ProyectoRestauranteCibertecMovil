package com.example.restaurante.data.repository

import android.app.Application
import com.example.restaurante.data.room.entity.Tarjeta

class TarjetaRepository (application: Application) : BaseRepository(application){

    suspend fun getTarjetas() = apiClient.getTarjetas()
    suspend fun saveTarjeta(tarjeta: Tarjeta) = apiClient.saveTarjeta(tarjeta)
    suspend fun updateTarjeta(tarjeta: Tarjeta) = apiClient.updateTarjeta(tarjeta)

}