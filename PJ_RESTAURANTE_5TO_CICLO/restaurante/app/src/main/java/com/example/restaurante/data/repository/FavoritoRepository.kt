package com.example.restaurante.data.repository

import android.app.Application
import com.example.restaurante.data.room.entity.Favorito

class FavoritoRepository (application: Application) : BaseRepository(application) {
    suspend fun getFavorito(id: Int) = apiClient.getFavorito(id)
    suspend fun saveFavorito(favorito: Favorito) =  apiClient.saveFavorito(favorito)
    suspend fun deleteFavorito(favorito: Favorito) = apiClient.deleteFavorito(favorito)
}