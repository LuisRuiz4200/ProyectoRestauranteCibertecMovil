package com.example.restaurante.presentation.confirmacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.databinding.ActivityConfirmacionBinding
import com.example.restaurante.domain.viewmodel.PedidoViewModel
import com.example.restaurante.presentation.main.MainTabActivity

class ConfirmacionActivity : AppCompatActivity() {
    private lateinit var binding : ActivityConfirmacionBinding
    private lateinit var database: BDPolleria
    private lateinit var pedidoViewModel : PedidoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }

    private fun initValues() {
        //Instancias
        database = BDPolleria.getInstancia(this)
        pedidoViewModel = ViewModelProvider(this)[PedidoViewModel::class.java]

        // Obtener información necesaria y almacenar en un objeto Pedido
        var items = database.cartDao().getAll()
        var pedido = database.pedidoDao().getAll().last()
        var usuario = SharedPreferences.getPrefUsuario(this)!!
        pedido.id_usuario_cliente = usuario.id_usuario
        pedido.carts = items
        var cant = 0.0
        for(item in pedido.carts)
            cant += (item.cantidad_producto * item.preciouni_producto)
        pedido.monto_compra = cant

        // Enviar objeto a API
        pedidoViewModel.savePedido(pedido)

        // Limpiar infomación temporal, Carrito y Pedido
        database.cartDao().deleteAll()
        database.pedidoDao().deleteAll()

        binding.btnRegresar.setOnClickListener {
            startActivity(Intent(this, MainTabActivity::class.java))
            finish()
        }
    }

    private fun initObservers(){
        pedidoViewModel.savePedido.observe(this){

        }
    }
}