package com.example.restaurante.presentation.catalogo.Details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.data.room.entity.Cart
import com.example.restaurante.data.room.entity.Favorito
import com.example.restaurante.data.room.entity.Producto
import com.example.restaurante.data.room.entity.Usuario
import com.example.restaurante.databinding.ActivityDetailsBinding
import com.example.restaurante.domain.viewmodel.CartViewModel
import com.example.restaurante.domain.viewmodel.FavoritoViewModel
import com.example.restaurante.domain.viewmodel.ProductoViewModel

class DetalleProductoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: ProductoViewModel
    private lateinit var database: BDPolleria
    private lateinit var producto : Producto
    private lateinit var cartViewModel: CartViewModel
    private lateinit var favoritosViewModel : FavoritoViewModel
    private var usuario = Usuario()

    var cantidad : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }

    private fun initValues() {
        // Mostrar producto
        val id = intent.getIntExtra("id_producto", 0)
        producto = Producto(id_producto = id)
        viewModel = ViewModelProvider(this)[ProductoViewModel::class.java]
        favoritosViewModel = ViewModelProvider(this)[FavoritoViewModel::class.java]
        usuario = SharedPreferences.getPrefUsuario(this)!!
        database = BDPolleria.getInstancia(this)
        // Controlar cantidad
        countControl()
        // Back Arrow
        backArrow()
        // Add to Cart
        addToCart(producto)

        binding.btnAddFavorites.setOnClickListener {
            val favorito = Favorito(id_producto = producto.id_producto, id_usuario = usuario.id_usuario)
            favoritosViewModel.saveFavorito(favorito)
        }

    }

    private fun initObservers(){
        viewModel.getProductosById.observe(this){productoObtenido ->
            productoObtenido?.let {
                // Actualizar el objeto producto con los datos obtenidos
                producto.id_producto = it.id_producto
                producto.nom_producto = it.nom_producto
                producto.preciouni_producto = it.preciouni_producto
                producto.des_producto = it.des_producto
                producto.imagen_producto = it.imagen_producto
                // Después de obtener y actualizar los datos del producto, llama a setDetails(producto)
                setDetails(producto)
            }
        }

        viewModel.getProductosById(producto.id_producto)

        favoritosViewModel.saveFavorito.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun setDetails(producto: Producto) {
        binding.tvNombre.text = producto.nom_producto
        binding.tvDescripcion.text = producto.des_producto
        binding.tvPrecio.text = String.format("%.2f",producto.preciouni_producto)
        Glide.with(this).load(producto.imagen_producto).into(binding.ivImg)
        val cart = database.cartDao().getCartByIdProducto(producto.id_producto)
        if(cart != null) //TODO
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
            if(item == null){ //TODO
                item = Cart()
            }
            item.id_producto = producto.id_producto
            item.cantidad_producto = binding.tvCount.text.toString().toInt()
            item.nom_producto = producto.nom_producto
            item.des_producto = producto.des_producto
            item.preciouni_producto = producto.preciouni_producto
            item.imagen_producto = producto.imagen_producto
            database.cartDao().insertCart(item)
            Toast.makeText(this, "Producto agregado.", Toast.LENGTH_LONG).show()
            cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
            cartViewModel.getCart()
        }
    }

}