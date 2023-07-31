package com.example.restaurante.presentation.confirmacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurante.R
import com.example.restaurante.databinding.ActivityConfirmacionBinding
import com.example.restaurante.presentation.catalogo.ListProductosActivity

class ConfirmacionActivity : AppCompatActivity() {
    private lateinit var binding : ActivityConfirmacionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues() {
        binding.btnRegresar.setOnClickListener {
            startActivity(Intent(this, ListProductosActivity::class.java))
            finish()
        }
    }
}