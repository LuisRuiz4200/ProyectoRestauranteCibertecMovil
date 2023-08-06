package com.example.restaurante.presentation.metodos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurante.R
import com.example.restaurante.databinding.ActivityMetodosPagoBinding

class MetodosPagoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMetodosPagoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMetodosPagoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}