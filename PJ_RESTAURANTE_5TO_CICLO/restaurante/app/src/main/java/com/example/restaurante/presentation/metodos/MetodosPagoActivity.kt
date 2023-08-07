package com.example.restaurante.presentation.metodos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.restaurante.R
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.data.room.entity.Pedido
import com.example.restaurante.databinding.ActivityMetodosPagoBinding
import com.example.restaurante.presentation.confirmacion.ConfirmacionActivity
import com.example.restaurante.presentation.perfil.MisTarjetas.MisTarjetasActivity

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
            AlertDialog.Builder(this)
                .setTitle("Confirmar metodo de pago")
                .setMessage("¿Seleccionar pago en efectivo?")
                .setPositiveButton("Sí"){ _, _ ->
                    pedido.id_medio_pago = 1
                    database.pedidoDao().insert(pedido)
                    startActivity(Intent(this, ConfirmacionActivity::class.java))
                    finish()
                }
                .setNegativeButton("No"){ _, _ ->

                }
                .setCancelable(true)
                .show()
        }

        binding.btnPagoTarjeta.setOnClickListener {
            startActivity(Intent(this, MisTarjetasActivity::class.java))
        }
    }
}