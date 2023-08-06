package com.example.restaurante.presentation.perfil.MisDirecciones.EditarDireccion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurante.R
import com.example.restaurante.databinding.ActivityEditarDireccionBinding

class EditarDireccionActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditarDireccionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarDireccionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues() {
        TODO("Not yet implemented")
    }
}