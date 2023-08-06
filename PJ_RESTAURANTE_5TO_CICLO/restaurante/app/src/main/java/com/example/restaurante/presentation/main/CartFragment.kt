package com.example.restaurante.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurante.R
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.data.room.entity.Cart
import com.example.restaurante.domain.viewmodel.CartViewModel
import com.example.restaurante.presentation.cart.CartAdapter
import com.example.restaurante.presentation.perfil.MisDirecciones.MisDireccionesActivity
import kotlinx.android.synthetic.main.activity_cart.view.btnContinuar
import kotlinx.android.synthetic.main.activity_cart.view.rvCart
import kotlinx.android.synthetic.main.activity_cart.view.tvPrecio

class CartFragment : Fragment() {
    private lateinit var database : BDPolleria
    private lateinit var cartAdapter : CartAdapter
    private lateinit var viewModel: CartViewModel
    private var cart : MutableList<Cart> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_cart,container,false)
        initValues(view)
        initObservers(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers(view)
    }
    override fun onResume() {
        super.onResume()
        initObservers(requireView())
//        calcPrecio(requireView()) // Recalcular el precio total cuando el fragmento vuelve a estar visible
    }

    private fun initValues(view: View) {
        database = BDPolleria.getInstancia(requireContext())
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]
        viewModel.init(database)
        cart = database.cartDao().getAll()
        cartAdapter = CartAdapter(cart, viewModel)
        view.rvCart.layoutManager = LinearLayoutManager(requireContext())
        view.rvCart.adapter = cartAdapter

        calcPrecio(view)


        view.btnContinuar.setOnClickListener {
            startActivity(Intent(requireContext(), MisDireccionesActivity::class.java).apply {
                putExtra("buyingMode",1)
            })
        }

    }

    private fun initObservers(view: View) {
        viewModel.getCart.observe(viewLifecycleOwner){
            cartAdapter.update(it)
            calcPrecio(view)
        }
        calcPrecio(view)
    }

    private fun calcPrecio(view: View) {
        var totalPrecio = 0.0
        var cart = database.cartDao().getAll()
        for (item in cart){
            val totalPorProducto = item.cantidad_producto * item.preciouni_producto
            totalPrecio += totalPorProducto
        }
        view.tvPrecio.text = String.format("%.2f",totalPrecio)
    }

}
