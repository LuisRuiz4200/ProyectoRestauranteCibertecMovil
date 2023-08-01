package com.example.restaurante.presentation.catalogo.Details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.data.room.entity.Cart
import com.example.restaurante.data.room.entity.Producto
import com.example.restaurante.databinding.ActivityDetailsBinding

class DetalleProductoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    private var database = BDPolleria.getInstancia(this)

    var cantidad : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues() {
        // Mostrar producto
        var id = intent.getIntExtra("id_producto", 0)
        var producto = database.productoDao().getProductoById(id)
        //Mostrar Producto
        setDetails(producto)
        // Controlar cantidad
        countControl()
        // Back Arrow
        backArrow()
        // Add to Cart
        addToCart(producto)

    }

    private fun setDetails(producto: Producto) {
        binding.tvNombre.text = producto.nom_producto
        binding.tvDescripcion.text = producto.des_producto
        binding.tvPrecio.text = String.format("%.2f",producto.preciouni_producto)
        var cart = database.cartDao().getCartByIdProducto(producto.id_producto)
        if(cart != null)
            binding.tvCount.text = cart.cantidad_producto.toString()
    }

    private fun countControl() {
        binding.btnMinus.setOnClickListener {
            cantidad = binding.tvCount.text.toString().toInt()
            if(cantidad > 1)
                cantidad--
            binding.tvCount.text = cantidad.toString()
        }
        binding.btnPlus.setOnClickListener {
            cantidad = binding.tvCount.text.toString().toInt()
            if(cantidad < 99)
                cantidad++
            binding.tvCount.text = cantidad.toString()
        }
    }

    private fun backArrow() {
        binding.btnBackArrow.setOnClickListener {
            finish()
        }
    }

    private fun addToCart(producto: Producto) {
        binding.btnAddToCart.setOnClickListener {
            var item = database.cartDao().getCartByIdProducto(producto.id_producto)
            if(item == null){
                item = Cart()
            }
            item.id_producto = producto.id_producto
            item.cantidad_producto = binding.tvCount.text.toString().toInt()
            database.cartDao().insertCart(item)
            Toast.makeText(this, "Producto agregado", Toast.LENGTH_LONG).show()
        }
    }

}