package com.example.restaurante.domain.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.restaurante.data.repository.TarjetaRepository
import com.example.restaurante.data.room.entity.Tarjeta
import kotlinx.coroutines.launch

class TarjetaViewModel (application: Application) : AndroidViewModel(application){
    private var repository = TarjetaRepository(application)

    private val _tarjetas = MutableLiveData<List<Tarjeta>>()
    val getTarjetas : LiveData<List<Tarjeta>> = _tarjetas

    private val _saveTarjeta = MutableLiveData<String>()
    val saveTarjeta : LiveData<String> = _saveTarjeta

    private val _updateTarjeta = MutableLiveData<String>()
    val updateTarjeta : LiveData<String> = _updateTarjeta

    fun getTarjetas() = viewModelScope.launch {
        try {
            val result = repository.getTarjetas()
            _tarjetas.postValue(result)
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

    fun saveTarjeta(tarjeta: Tarjeta) = viewModelScope.launch {
        try {
            val result = repository.saveTarjeta(tarjeta)
            _saveTarjeta.postValue(result)
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

    fun updateTarjeta(tarjeta: Tarjeta) = viewModelScope.launch {
        try {
            val result = repository.updateTarjeta(tarjeta)
            _updateTarjeta.postValue(result)
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

}