package com.example.restaurante.domain.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.restaurante.data.repository.UsuarioRepository
import com.example.restaurante.data.room.entity.Usuario
import kotlinx.coroutines.launch

class UsuarioViewModel (application: Application) : AndroidViewModel(application){
    private var repository = UsuarioRepository(application)

    private val _login = MutableLiveData<Usuario>()
    val login : LiveData<Usuario> = _login

    private val _saveUsuario = MutableLiveData<String>()
    val saveUsuario : LiveData<String> = _saveUsuario

    private val _updateUsuario = MutableLiveData<String>()
    val updateUsuario : LiveData<String> = _updateUsuario

    private val _changePass = MutableLiveData<String>()
    val changePass : LiveData<String> = _changePass

    fun loginUsuario(usuario: Usuario) = viewModelScope.launch {
        try {
            val result = repository.loginUsuario(usuario)
            _login.postValue(result)
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

    fun saveUsuario(usuario: Usuario) = viewModelScope.launch {
        try {
            val result = repository.saveUsuario(usuario)
            _saveUsuario.postValue(result)
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

    fun updateUsuario(usuario: Usuario) = viewModelScope.launch {
        try {
            val result = repository.updateUsuario(usuario)
            _updateUsuario.postValue(result)
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

    fun changePass(usuario: Usuario) = viewModelScope.launch {
        try {
            val result = repository.changePass(usuario)
            _changePass.postValue(result)
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }
}