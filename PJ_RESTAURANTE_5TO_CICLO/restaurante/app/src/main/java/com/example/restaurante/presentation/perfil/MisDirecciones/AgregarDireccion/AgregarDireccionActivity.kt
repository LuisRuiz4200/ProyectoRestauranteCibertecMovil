package com.example.restaurante.presentation.perfil.MisDirecciones.AgregarDireccion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurante.databinding.ActivityAgregarDireccionBinding

class AgregarDireccionActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAgregarDireccionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarDireccionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}