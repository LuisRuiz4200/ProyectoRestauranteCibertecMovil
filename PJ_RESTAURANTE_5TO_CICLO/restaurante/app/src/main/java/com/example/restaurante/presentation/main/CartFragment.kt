package com.example.restaurante.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurante.R
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.data.room.entity.Cart
import com.example.restaurante.presentation.cart.CartAdapter
import kotlinx.android.synthetic.main.activity_cart.view.btnContinuar
import kotlinx.android.synthetic.main.activity_cart.view.rvCart
import kotlinx.android.synthetic.main.activity_cart.view.tvPrecio

class CartFragment : Fragment() {
//    private lateinit var viewModel: ProductoViewModel // <---Crear CartViewModel
    private lateinit var database : BDPolleria
    private lateinit var cartAdapter : CartAdapter
    private var cart : MutableList<Cart> = ArrayList()
//    private var productos : MutableList<Producto> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_cart,container,false)
        initValues(view)
        initObservers()
        return view
    }

    private fun initValues(view: View) {
        database = BDPolleria.getInstancia(requireContext())
        cart = database.cartDao().getAll()
        cartAdapter = CartAdapter(cart)
        view.rvCart.layoutManager = LinearLayoutManager(requireContext())
        view.rvCart.adapter = cartAdapter
        var totalPrecio = 0.0
        for (item in cart){
            val totalPorProducto = item.cantidad_producto * item.preciouni_producto
            totalPrecio += totalPorProducto
        }
        view.tvPrecio.text = String.format("%.2f",totalPrecio)

        view.btnContinuar.setOnClickListener {
            /* Llamar a direccion TODO */

        }
    }

    private fun initObservers() {

    }

}
