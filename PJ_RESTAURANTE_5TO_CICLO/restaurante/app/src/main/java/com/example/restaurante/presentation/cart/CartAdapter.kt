package com.example.restaurante.presentation.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurante.R
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.data.room.entity.Cart
import com.example.restaurante.data.room.entity.Producto

class CartAdapter (var items : MutableList<Cart>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProducto: TextView = itemView.findViewById(R.id.rvCartNombre)
        val tvCantidad: TextView = itemView.findViewById(R.id.rvCartCantidad)
        val tvDescripcion: TextView = itemView.findViewById(R.id.rvCartPrecio)
        val tvPrecio: TextView = itemView.findViewById(R.id.rvCartPrecio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvProducto.text = item.nom_producto
        holder.tvCantidad.text = item.cantidad_producto.toString()
        holder.tvDescripcion.text = item.preciouni_producto.toString()
        holder.tvPrecio.text = item.preciouni_producto.toString()
    }
}