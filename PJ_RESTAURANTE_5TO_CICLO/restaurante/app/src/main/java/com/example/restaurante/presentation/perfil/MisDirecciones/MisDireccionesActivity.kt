package com.example.restaurante.presentation.perfil.MisDirecciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.data.room.entity.Direccion
import com.example.restaurante.databinding.ActivityMisDireccionesBinding
import com.example.restaurante.data.room.entity.Usuario
import com.example.restaurante.domain.viewmodel.DireccionViewModel

class MisDireccionesActivity : AppCompatActivity(), MisDireccionesAdapter.ICard {
    private lateinit var binding : ActivityMisDireccionesBinding
    private lateinit var viewModel: DireccionViewModel
    private lateinit var database : BDPolleria
    private lateinit var direccionAdapter :MisDireccionesAdapter
    private lateinit var usuario : Usuario
    private var listDirecciones : MutableList<Direccion> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMisDireccionesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }

    private fun initValues() {
        viewModel = ViewModelProvider(this)[DireccionViewModel::class.java]
        database = BDPolleria.getInstancia(this)
        usuario = SharedPreferences.getPrefUsuario(this)!!
        var buyingMode = intent.getIntExtra("buyingMode", 0)
        direccionAdapter = MisDireccionesAdapter(listDirecciones, this, buyingMode)
        binding.rvDirecciones.layoutManager = LinearLayoutManager(this)
        binding.rvDirecciones.adapter = direccionAdapter

    }

    private fun initObservers(){
        viewModel.getDirecciones.observe(this){
            direccionAdapter.update(it)
        }
        viewModel.getDirecciones(usuario.id_usuario)
    }

    override fun onCardClick(item: Direccion) {
        println("HOLIIIIIIIIII :V ${item.nombre_direntrega}")

    }
}