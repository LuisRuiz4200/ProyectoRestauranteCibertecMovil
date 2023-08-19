package com.example.restaurante.presentation.catalogo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurante.data.room.entity.Producto
import com.example.restaurante.databinding.ActivityListProductosBinding
import com.example.restaurante.presentation.catalogo.Details.DetalleProductoActivity

class ListProductosActivity : AppCompatActivity(), ListProductosAdapter.ICard {
    private lateinit var binding:ActivityListProductosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityListProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }
    private fun initValues(){
    }

    private fun initObservers(){
    }
    override fun onCardClick(item: Producto) {
        startActivity(Intent(this, DetalleProductoActivity::class.java).apply {
            putExtra("id_producto", item.id_producto)
        })
    }
}