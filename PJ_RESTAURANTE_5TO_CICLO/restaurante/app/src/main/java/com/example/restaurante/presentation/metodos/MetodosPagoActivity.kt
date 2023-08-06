package com.example.restaurante.presentation.metodos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurante.R
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.databinding.ActivityMetodosPagoBinding

class MetodosPagoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMetodosPagoBinding
    private lateinit var database: BDPolleria

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMetodosPagoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues() {
        database = BDPolleria.getInstancia(this)
        var pedido = database.pedidoDao().getAll().last()

        binding.btnPagoEfectivo.setOnClickListener {
            println("${pedido.id_pedido} ${pedido.id_dirEntrega}")
        }
    }
}