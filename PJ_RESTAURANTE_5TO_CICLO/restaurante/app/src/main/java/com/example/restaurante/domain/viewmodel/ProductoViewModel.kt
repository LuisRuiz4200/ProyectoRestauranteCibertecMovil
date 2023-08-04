package com.example.restaurante.domain.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.restaurante.data.repository.ProductoRepository
import com.example.restaurante.data.room.entity.Producto
import kotlinx.coroutines.launch

class ProductoViewModel (application: Application) : AndroidViewModel(application){
    private var repository = ProductoRepository(application)

    private val _productos = MutableLiveData<List<Producto>>()
    val getProductos : LiveData<List<Producto>> = _productos

    private val _producto = MutableLiveData<Producto>()
    val getProducto : LiveData<Producto> = _producto

    fun obtenerProductos() = viewModelScope.launch {
        try {
            val result = repository.getProductos()
            _productos.postValue(result)

        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

    fun obtenerProducto(idProducto: Int) = viewModelScope.launch {
        try {
            val result = repository.getProductosById(idProducto)
            _producto.postValue(result)
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }



}