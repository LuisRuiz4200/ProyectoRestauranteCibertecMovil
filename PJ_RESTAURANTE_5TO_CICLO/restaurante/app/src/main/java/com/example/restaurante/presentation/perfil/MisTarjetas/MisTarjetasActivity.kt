package com.example.restaurante.presentation.perfil.MisTarjetas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.data.room.entity.Pedido
import com.example.restaurante.data.room.entity.Tarjeta
import com.example.restaurante.data.room.entity.Usuario
import com.example.restaurante.databinding.ActivityMisTarjetasBinding
import com.example.restaurante.domain.viewmodel.TarjetaViewModel
import com.example.restaurante.presentation.confirmacion.ConfirmacionActivity
import com.example.restaurante.presentation.perfil.MisTarjetas.AgregarTarjeta.AgregarTarjetaActivity

class MisTarjetasActivity : AppCompatActivity(), MisTarjetasAdapter.ICard {
    private lateinit var binding : ActivityMisTarjetasBinding
    private lateinit var database : BDPolleria
    private lateinit var tarjetaViewModel: TarjetaViewModel
    private var listadoTarjetas : MutableList<Tarjeta> = ArrayList()
    private lateinit var tarjetaAdapter :MisTarjetasAdapter
    private var buyingMode = 0
    private lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMisTarjetasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        tarjetaViewModel.getTarjetas(usuario.id_usuario)
    }

    private fun initValues() {
        tarjetaViewModel = ViewModelProvider(this)[TarjetaViewModel::class.java]
        database = BDPolleria.getInstancia(this)
        usuario = SharedPreferences.getPrefUsuario(this)!!
        buyingMode = intent.getIntExtra("buyingMode", 0)

        tarjetaAdapter = MisTarjetasAdapter(listadoTarjetas, buyingMode, this, tarjetaViewModel)
        binding.rvTarjetas.layoutManager = LinearLayoutManager(this)
        binding.rvTarjetas.adapter = tarjetaAdapter

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, AgregarTarjetaActivity::class.java))
        }
    }

    private fun initObservers() {
        tarjetaViewModel.getTarjetas.observe(this){
            tarjetaAdapter.update(it)
        }
        tarjetaViewModel.deleteTarjeta.observe(this){
            println("Holi Exterminated")

            tarjetaViewModel.getTarjetas(usuario.id_usuario)
        }
        tarjetaViewModel.getTarjetas(usuario.id_usuario)
    }

    override fun onCardClick(item: Tarjeta) {
        AlertDialog.Builder(this)
            .setTitle("Confirmar tarjeta")
            .setMessage("¿Seleccionar ${item.nombre_tarjeta} como tarjeta de pago?")
            .setPositiveButton("Sí"){ _, _ ->
                var pedido = Pedido()
                if(database.pedidoDao().getAll().isNotEmpty()){
                    val lastPedido = database.pedidoDao().getAll().last()
                    if(lastPedido.id_usuario_cliente == 0)
                        pedido = lastPedido
                }
                pedido.id_tarjeta = item.id_tarjeta
                pedido.id_medio_pago = 2
                database.pedidoDao().insert(pedido)
                startActivity(Intent(this, ConfirmacionActivity::class.java))
                finish()
            }
            .setNegativeButton("No"){ _, _ ->

            }
            .setCancelable(true)
            .show()
    }
}