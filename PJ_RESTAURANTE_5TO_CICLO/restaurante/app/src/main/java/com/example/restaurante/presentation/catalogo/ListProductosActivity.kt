package com.example.restaurante.presentation.catalogo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.data.room.entity.Producto
import com.example.restaurante.databinding.ActivityListProductosBinding
import com.example.restaurante.presentation.Perfil.PerfilUsuarioActivity
import com.example.restaurante.presentation.catalogo.Details.DetalleProductoActivity

class ListProductosActivity : AppCompatActivity() {
    private lateinit var binding:ActivityListProductosBinding
    private var listadoProducto : MutableList<Producto> = ArrayList()
    private lateinit var database : BDPolleria
    private  lateinit var productoAdapter :ListProductosAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityListProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }
    private fun initValues(){
        listadoProducto.add(Producto(1,2,"1/4 de Pollo","Con Gaseosa",10.50,50,null))
        listadoProducto.add(Producto(2,2,"1/8 de Pollo","Con Ensalada",6.50,30,null))
        listadoProducto.add(Producto(3,2,"1/2 de Pollo","Con Helado",24.34,42,null))
        listadoProducto.add(Producto(4,2,"1 de Pollo","Con Gaseosa Y Ensalada",65.50,100,null))
        listadoProducto.add(Producto(5,2,"1 de Pollo","Con Gaseosa Y Ensalada",65.50,100,null))
        listadoProducto.add(Producto(6,2,"1 de Pollo","Con Gaseosa Y Ensalada",65.50,100,null))
        database =BDPolleria.getInstancia(this)
        database.productoDao().insert(listadoProducto)
        productoAdapter = ListProductosAdapter(database.productoDao().getAll())
////        binding.rvProducto.layoutManager=LinearLayoutManager(applicationContext)
//        val layoutManager
//                = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvProducto.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvProducto.adapter=productoAdapter
        setNombreUsuario()

        binding.btnPerfil.setOnClickListener {
            startActivity(Intent(this, PerfilUsuarioActivity::class.java))
        }

        binding.btnMenu.setOnClickListener{
            // Accion temporal para mostrar Detalle Producto
            startActivity(Intent(this,DetalleProductoActivity::class.java))
        }
    }

    private fun setNombreUsuario(){
        var usuario = SharedPreferences.getPrefUsuario(this)
        binding.tvUsuario.text = "Hola ${usuario?.nom_usuario}"
    }
}