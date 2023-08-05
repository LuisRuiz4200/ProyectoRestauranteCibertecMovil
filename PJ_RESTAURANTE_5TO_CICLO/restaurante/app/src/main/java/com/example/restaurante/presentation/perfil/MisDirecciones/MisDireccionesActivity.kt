package com.example.restaurante.presentation.perfil.MisDirecciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.data.room.entity.Producto
import com.example.restaurante.databinding.ActivityMisDireccionesBinding

class MisDireccionesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMisDireccionesBinding
    private var listadoProducto : MutableList<Producto> = ArrayList()
    private lateinit var database : BDPolleria
    private  lateinit var productoAdapter :MisDireccionesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMisDireccionesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues() {
//        listadoProducto.add(Producto(1,2,"1/4 de Pollo","Con Gaseosa",10.50,50,null))
//        listadoProducto.add(Producto(2,2,"1/8 de Pollo","Con Ensalada",6.50,30,null))
//        listadoProducto.add(Producto(3,2,"1/2 de Pollo","Con Helado",24.34,42,null))
//        listadoProducto.add(Producto(4,2,"1 de Pollo","Con Gaseosa Y Ensalada",65.50,100,null))
//        listadoProducto.add(Producto(5,2,"1 de Pollo","Con Gaseosa Y Ensalada",65.50,100,null))
//        listadoProducto.add(Producto(6,2,"1 de Pollo","Con Gaseosa Y Ensalada",65.50,100,null))
        database = BDPolleria.getInstancia(this)
        database.productoDao().insert(listadoProducto)
        productoAdapter = MisDireccionesAdapter(database.productoDao().getAll())
//        LinearLayoutManager(applicationContext)
//        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvDirecciones.layoutManager = LinearLayoutManager(this)
        binding.rvDirecciones.adapter=productoAdapter
    }
}