package com.example.restaurante.domain.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.restaurante.data.repository.CategoriaRepository
import com.example.restaurante.data.room.entity.Categoria
import kotlinx.coroutines.launch

class CategoriaViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = CategoriaRepository(application)

    private val _categorias = MutableLiveData<List<Categoria>>()
    var getCategorias : LiveData<List<Categoria>> = _categorias

    fun getCategorias() = viewModelScope.launch {
        try {
            val result = repository.getCategorias()
            _categorias.postValue(result)

        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }
}