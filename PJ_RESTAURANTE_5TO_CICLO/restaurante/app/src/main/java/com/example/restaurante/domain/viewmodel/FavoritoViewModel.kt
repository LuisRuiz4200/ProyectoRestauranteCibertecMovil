package com.example.restaurante.domain.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.restaurante.data.repository.FavoritoRepository
import com.example.restaurante.data.room.entity.Favorito
import kotlinx.coroutines.launch

class FavoritoViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = FavoritoRepository(application)

    private val _getFavorito = MutableLiveData<List<Favorito>>()
    var getFavorito : LiveData<List<Favorito>> = _getFavorito

    private val _saveFavorito = MutableLiveData<String>()
    var saveFavorito : LiveData<String> = _saveFavorito

    private val _deleteFavorito = MutableLiveData<String>()
    var deleteFavorito : LiveData<String> = _deleteFavorito

    fun getFavorito(id: Int) = viewModelScope.launch {
        try {
            val result = repository.getFavorito(id)
            _getFavorito.postValue(result)
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

    fun saveFavorito(favorito: Favorito) = viewModelScope.launch {
        try {
            val result = repository.saveFavorito(favorito)
            _saveFavorito.postValue(result)
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

    fun deleteFavorito(favorito: Favorito) = viewModelScope.launch {
        try {
            val result = repository.deleteFavorito(favorito)
            _deleteFavorito.postValue(result)
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }
}