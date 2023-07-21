package com.example.restaurante.presentation.registro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurante.R
import com.example.restaurante.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues(){

    }
}