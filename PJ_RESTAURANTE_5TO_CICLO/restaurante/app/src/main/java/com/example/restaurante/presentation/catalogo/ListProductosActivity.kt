package com.example.restaurante.presentation.catalogo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.data.room.entity.Producto
import com.example.restaurante.databinding.ActivityListProductosBinding
import com.example.restaurante.domain.viewmodel.ProductoViewModel
import com.example.restaurante.presentation.cart.CartActivity
import com.example.restaurante.presentation.perfil.PerfilUsuarioActivity
import com.example.restaurante.presentation.catalogo.Details.DetalleProductoActivity
import com.example.restaurante.presentation.confirmacion.ConfirmacionActivity

class ListProductosActivity : AppCompatActivity(), ListProductosAdapter.ICard {
    private lateinit var binding:ActivityListProductosBinding

    private lateinit var database : BDPolleria
    private  lateinit var productoAdapter :ListProductosAdapter

    private lateinit var viewModel: ProductoViewModel
    private var lstProductos : MutableList<Producto> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityListProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productosTemporales()
        initValues()
        initObservers()
    }
    private fun initValues(){
        //Inicializar viewModel
        viewModel = ViewModelProvider(this).get(ProductoViewModel::class.java)
        setNombreUsuario()
        productoAdapter = ListProductosAdapter(lstProductos, this)
//          LinearLayoutManager(applicationContext)
//          LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvProducto.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvProducto.adapter=productoAdapter

        binding.btnPerfil.setOnClickListener {
            startActivity(Intent(this, PerfilUsuarioActivity::class.java))
        }

        binding.btnMenu.setOnClickListener{
            // Accion temporal para mostrar Detalle Producto
            startActivity(Intent(this,DetalleProductoActivity::class.java))
        }

        binding.btnOpcPromociones.setOnClickListener {
            // Accion temporal para mostrar Detalle Producto
            startActivity(Intent(this, CartActivity::class.java))
        }

        binding.btnOpcParrillas.setOnClickListener {
            startActivity(Intent(this, ConfirmacionActivity::class.java))
        }
    }

    private fun initObservers(){
        viewModel.getProductos.observe(this){
            productoAdapter.update(it)
        }

        viewModel.obtenerProductos()
    }

    private fun setNombreUsuario(){
        var usuario = SharedPreferences.getPrefUsuario(this)
        binding.tvUsuario.text = "Hola ${usuario?.nom_usuario}"
    }

    private fun productosTemporales(){
//        var listadoProducto : MutableList<Producto> = ArrayList()
//        listadoProducto.add(Producto(1,2,"1/4 de Pollo","Con Gaseosa",10.50,50))
//        listadoProducto.add(Producto(2,2,"1/8 de Pollo","Con Ensalada",6.50,30))
//        listadoProducto.add(Producto(3,2,"1/2 de Pollo","Con Helado",24.34,42))
//        listadoProducto.add(Producto(4,2,"1 de Pollo","Con Gaseosa Y Ensalada",65.50,100))
//        listadoProducto.add(Producto(5,2,"1 de Pollo","Con Gaseosa Y Ensalada",65.50,100))
//        listadoProducto.add(Producto(6,2,"1 de Pollo","Con Gaseosa Y Ensalada",65.50,100))
//        database =BDPolleria.getInstancia(this)
//        database.productoDao().insert(listadoProducto)
    }

    override fun onCardClick(item: Producto) {
        startActivity(Intent(this, DetalleProductoActivity::class.java).apply {
            putExtra("id_producto", item.id_producto)
        })
    }


}