package com.example.restaurante.presentation.Perfil.MisDirecciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurante.databinding.ActivityMisDireccionesBinding

class MisDireccionesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMisDireccionesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMisDireccionesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}