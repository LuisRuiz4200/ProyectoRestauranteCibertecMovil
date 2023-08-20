package com.example.restaurante.presentation.perfil.MisFavoritos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurante.R
import com.example.restaurante.databinding.ActivityMisFavoritosBinding

class MisFavoritosActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMisFavoritosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMisFavoritosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }

    private fun initValues() {

    }

    private fun initObservers() {

    }
}