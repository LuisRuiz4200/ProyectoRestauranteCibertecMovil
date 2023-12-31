package com.example.restaurante.domain.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.restaurante.data.repository.DireccionRepository
import com.example.restaurante.data.room.entity.Direccion
import kotlinx.coroutines.launch

class DireccionViewModel (application: Application) : AndroidViewModel(application) {
    private var repository = DireccionRepository(application)

    private val _direcciones = MutableLiveData<List<Direccion>>()
    var getDirecciones: LiveData<List<Direccion>> = _direcciones

    private val _saveDireccion = MutableLiveData<String>()
    var saveDireccion: LiveData<String> = _saveDireccion

    private val _updateDireccion = MutableLiveData<String>()
    var updateDireccion: LiveData<String> = _updateDireccion

    private val _deleteDireccion = MutableLiveData<String>()
    var deleteDireccion : LiveData<String> = _deleteDireccion

    fun getDirecciones(id: Int) = viewModelScope.launch {
        try {
            val result = repository.getDirecciones(id)
            _direcciones.postValue(result)

        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

    fun saveDireccion(direccion: Direccion) = viewModelScope.launch {
        try {
            val result = repository.saveDireccion(direccion)
            _saveDireccion.postValue(result)

        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

    fun updateDireccion(direccion: Direccion) = viewModelScope.launch {
        try {
            val result = repository.updateDireccion(direccion)
            _updateDireccion.postValue(result)

        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

    fun deleteDireccion(id: Int) = viewModelScope.launch {
        try {
            val result = repository.deleteDireccion(id)
            _deleteDireccion.postValue(result)

        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }
}