package com.example.restaurante.presentation.Perfil.EditarPerfil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurante.databinding.ActivityEditarPerfilBinding

class EditarPerfilActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditarPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues() {
        TODO("Not yet implemented")
    }
}