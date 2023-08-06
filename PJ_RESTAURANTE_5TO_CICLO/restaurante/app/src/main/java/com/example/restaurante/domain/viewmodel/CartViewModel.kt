package com.example.restaurante.domain.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.data.room.entity.Cart

class CartViewModel : ViewModel() {
    private lateinit var database: BDPolleria
    private val _carts = MutableLiveData<List<Cart>>()
    val getCart : LiveData<List<Cart>> get() = _carts

    fun init(database: BDPolleria){
        this.database = database
        getCart()
    }

    fun getCart(){
        try {
            _carts.postValue(database.cartDao().getAll())
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }
}