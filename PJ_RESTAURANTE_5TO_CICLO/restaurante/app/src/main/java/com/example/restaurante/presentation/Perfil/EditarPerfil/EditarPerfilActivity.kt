package com.example.restaurante.presentation.Perfil.EditarPerfil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurante.databinding.ActivityEditarPerfilBinding
import com.example.restaurante.presentation.Perfil.CambiarContrasena.CambiarContrasenaActivity

class EditarPerfilActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditarPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues() {
        binding.btnCambiarContrasena.setOnClickListener {
            startActivity(Intent(this,CambiarContrasenaActivity::class.java))
        }
    }
}