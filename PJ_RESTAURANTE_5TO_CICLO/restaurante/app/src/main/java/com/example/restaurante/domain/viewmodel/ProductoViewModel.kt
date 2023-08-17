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

    private val _getProductosById = MutableLiveData<Producto>()
    val getProductosById : LiveData<Producto> = _getProductosById

    private val _getProductosByCategoria = MutableLiveData<List<Producto>>()
    val getProductosByCategoria : LiveData<List<Producto>> = _getProductosByCategoria

    private val _getProductosByNombre = MutableLiveData<List<Producto>>()
    val getProductosByNombre : LiveData<List<Producto>> = _getProductosByNombre

    fun obtenerProductos() = viewModelScope.launch {
        try {
            val result = repository.getProductos()
            _productos.postValue(result)

        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

    fun getProductosById(idProducto: Int) = viewModelScope.launch {
        try {
            val result = repository.getProductosById(idProducto)
            _getProductosById.postValue(result)
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

    fun getProductosByCategoria(id: Int) = viewModelScope.launch {
        try{
            val result = repository.getProductosByCategoria(id)
            _getProductosByCategoria.postValue(result)
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

    fun getProductosByNombre(nombre: String) = viewModelScope.launch {
        try{
            val result = repository.getProductosByNombre(nombre)
            _getProductosByNombre.postValue(result)
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }
}