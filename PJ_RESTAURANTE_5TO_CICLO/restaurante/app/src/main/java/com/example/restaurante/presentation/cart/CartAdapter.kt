package com.example.restaurante.presentation.cart

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restaurante.R
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.data.room.entity.Cart
import com.example.restaurante.data.room.entity.Direccion
import com.example.restaurante.data.room.entity.Producto
import com.example.restaurante.domain.viewmodel.CartViewModel
import com.example.restaurante.presentation.catalogo.Details.DetalleProductoActivity
import kotlinx.android.synthetic.main.item_cart.view.btnEditar
import kotlinx.android.synthetic.main.item_cart.view.btnEliminar

class CartAdapter (var items : MutableList<Cart>, private val viewModel: CartViewModel) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    private lateinit var database : BDPolleria
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProducto: TextView = itemView.findViewById(R.id.rvCartNombre)
        val tvCantidad: TextView = itemView.findViewById(R.id.rvCartCantidad)
        val tvDescripcion: TextView = itemView.findViewById(R.id.rvCartPrecio)
        val tvPrecio: TextView = itemView.findViewById(R.id.rvCartPrecio)
        val rvCartImg: ImageFilterView = itemView.findViewById(R.id.rvCartImg)

        init {
            database = BDPolleria.getInstancia(itemView.context)
        }
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
        holder.tvPrecio.text = String.format("%.2f",item.preciouni_producto)
        Glide.with(holder.itemView.context).load(item.imagen_producto).into(holder.rvCartImg)

        holder.itemView.btnEditar.setOnClickListener {
            val item = items[holder.adapterPosition]
            holder.itemView.context.startActivity(
                Intent(holder.itemView.context, DetalleProductoActivity::class.java).apply {
                    putExtra("id_producto", item.id_producto)
                }
            )
        }

        holder.itemView.btnEliminar.setOnClickListener {
            val item = items[holder.adapterPosition]
            database.cartDao().deleteProducto(item.id_producto)
            viewModel.getCart()
        }
    }

    fun update(newItems : List<Cart>){
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }
}