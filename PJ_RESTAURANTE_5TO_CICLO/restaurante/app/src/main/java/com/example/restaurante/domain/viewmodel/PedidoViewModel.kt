package com.example.restaurante.domain.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.restaurante.data.repository.PedidoRepository
import com.example.restaurante.data.room.entity.Pedido
import kotlinx.coroutines.launch

class PedidoViewModel (application: Application) : AndroidViewModel(application){
    private var repository = PedidoRepository(application)

    private val _savePedido = MutableLiveData<String>()
    val savePedido : LiveData<String> = _savePedido

    private val _getPedidoByUser = MutableLiveData<List<Pedido>>()
    var getPedidoByUser : LiveData<List<Pedido>> = _getPedidoByUser

    fun savePedido(pedido: Pedido) = viewModelScope.launch{
        try {
            val result = repository.savePedido(pedido)
            _savePedido.postValue(result)
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

    fun getPedidoByUser(id: Int) = viewModelScope.launch {
        try {
            val result = repository.getPedidoByUser(id)
            _getPedidoByUser.postValue(result)
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }
}