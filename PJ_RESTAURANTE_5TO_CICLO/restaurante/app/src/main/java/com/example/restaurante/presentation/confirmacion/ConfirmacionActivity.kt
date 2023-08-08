package com.example.restaurante.presentation.confirmacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.restaurante.R
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.databinding.ActivityConfirmacionBinding
import com.example.restaurante.presentation.catalogo.ListProductosActivity
import com.example.restaurante.presentation.main.MainTabActivity

class ConfirmacionActivity : AppCompatActivity() {
    private lateinit var binding : ActivityConfirmacionBinding
    private lateinit var database: BDPolleria


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues() {
        database = BDPolleria.getInstancia(this)
        var items = database.cartDao().getAll()
        var pedido = database.pedidoDao().getAll().last()
        var usuario = SharedPreferences.getPrefUsuario(this)!!
        pedido.id_usuario_cliente = usuario.id_usuario
        pedido.listaCarts = items
        // Enviar a API TODO
        // Enviar lista de Cart + Datos de Pedido

        items.forEach {
            Log.d("MainActivity", "idProducto: ${it.id_producto}, cantidad: ${it.cantidad_producto}")
        }

//        database.cartDao().deleteAll()
//        database.pedidoDao().deleteAll()

        binding.btnRegresar.setOnClickListener {

            Thread.sleep(1000)
            startActivity(Intent(this, MainTabActivity::class.java))
            finish()
        }
    }
}