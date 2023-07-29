package com.example.restaurante.presentation.Perfil.CambiarContrasena

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurante.databinding.ActivityCambiarContrasenaBinding

class CambiarContrasenaActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCambiarContrasenaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCambiarContrasenaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}