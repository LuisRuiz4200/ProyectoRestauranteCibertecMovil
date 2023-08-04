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
import com.example.restaurante.data.room.entity.Producto
import com.example.restaurante.domain.viewmodel.ProductoViewModel
import com.example.restaurante.presentation.cart.CartAdapter
import kotlinx.android.synthetic.main.activity_cart.view.rvCart

class CartFragment : Fragment() {
    private lateinit var viewModel: ProductoViewModel // <---Crear CartViewModel
    private lateinit var database : BDPolleria
    private lateinit var cartAdapter : CartAdapter
    private var cart : MutableList<Cart> = ArrayList()
    private var productos : MutableList<Producto> = ArrayList()

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
        database = BDPolleria.getInstancia(view.context)
        cart = database.cartDao().getAll()
        cartAdapter = CartAdapter(cart)
        view.rvCart.layoutManager = LinearLayoutManager(view.context)
        view.rvCart.adapter = cartAdapter


    }

    private fun initObservers() {

    }

}
