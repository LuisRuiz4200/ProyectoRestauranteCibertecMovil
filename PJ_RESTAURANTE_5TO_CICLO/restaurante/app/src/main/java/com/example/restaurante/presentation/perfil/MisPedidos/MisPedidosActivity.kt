package com.example.restaurante.presentation.perfil.MisPedidos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.data.room.entity.Pedido
import com.example.restaurante.data.room.entity.Usuario
import com.example.restaurante.databinding.ActivityMisPedidosBinding
import com.example.restaurante.domain.viewmodel.PedidoViewModel

class MisPedidosActivity : AppCompatActivity(), MisPedidosAdapter.ICard {
    private lateinit var binding : ActivityMisPedidosBinding
    private lateinit var viewModelPedido: PedidoViewModel
    private lateinit var pedidoAdapter: MisPedidosAdapter
    private var lstPedido : MutableList<Pedido> = ArrayList()
    private var usuario = Usuario()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMisPedidosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }

    private fun initValues() {
        viewModelPedido = ViewModelProvider(this)[PedidoViewModel::class.java]
        usuario = SharedPreferences.getPrefUsuario(this)!!

        pedidoAdapter = MisPedidosAdapter(lstPedido, this)
        binding.rvPedido.layoutManager = LinearLayoutManager(this)
        binding.rvPedido.adapter = pedidoAdapter
    }

    private fun initObservers() {
        viewModelPedido.getPedidoByUser.observe(this){
            pedidoAdapter.update(it)
        }
        viewModelPedido.getPedidoByUser(usuario.id_usuario)
    }

    override fun onCardClick(item: Pedido) {
        TODO("Not yet implemented")
    }
}