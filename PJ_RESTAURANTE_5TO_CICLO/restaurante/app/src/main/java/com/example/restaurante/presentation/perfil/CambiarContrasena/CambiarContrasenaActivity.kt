package com.example.restaurante.presentation.perfil.CambiarContrasena

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurante.databinding.ActivityCambiarContrasenaBinding
import com.example.restaurante.presentation.perfil.MisDirecciones.AgregarDireccion.AgregarDireccionActivity

class CambiarContrasenaActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCambiarContrasenaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCambiarContrasenaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues() {
        binding.tvBack.setOnClickListener {
            startActivity(Intent(this,AgregarDireccionActivity::class.java))
        }
    }
}