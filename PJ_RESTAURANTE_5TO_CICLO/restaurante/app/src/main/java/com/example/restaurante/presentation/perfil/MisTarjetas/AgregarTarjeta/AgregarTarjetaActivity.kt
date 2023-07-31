package com.example.restaurante.presentation.perfil.MisTarjetas.AgregarTarjeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurante.databinding.ActivityAgregarTarjetaBinding

class AgregarTarjetaActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAgregarTarjetaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarTarjetaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues() {
        TODO("Not yet implemented")
    }
}