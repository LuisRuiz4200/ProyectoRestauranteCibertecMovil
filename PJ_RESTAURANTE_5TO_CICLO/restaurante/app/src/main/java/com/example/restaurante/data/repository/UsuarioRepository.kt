package com.example.restaurante.data.repository

import android.app.Application
import com.example.restaurante.data.room.entity.Usuario

class UsuarioRepository(application: Application) : BaseRepository(application) {
    suspend fun loginUsuario(usuario: Usuario) = apiClient.loginUsuario(usuario)
    suspend fun saveUsuario(usuario: Usuario) = apiClient.saveUsuario(usuario)
    suspend fun updateUsuario(usuario: Usuario) = apiClient.updateUsuario(usuario)
    suspend fun changePass(usuario: Usuario) = apiClient.changePass(usuario)
}