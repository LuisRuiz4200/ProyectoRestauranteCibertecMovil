package com.example.restaurante.presentation.perfil.MisDirecciones.AgregarDireccion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurante.databinding.ActivityAgregarDireccionBinding
import com.example.restaurante.domain.viewmodel.DireccionViewModel

class AgregarDireccionActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAgregarDireccionBinding
    private lateinit var direccionViewModel : DireccionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarDireccionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }

    private fun initValues() {
    }

    private fun initObservers(){

    }
}