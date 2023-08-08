package com.example.restaurante.presentation.catalogo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurante.R
import com.example.restaurante.data.room.entity.Producto

class ListProductosAdapter
    (var items : MutableList<Producto>, var iCard: ICard)
    : RecyclerView.Adapter<ListProductosAdapter.ViewHolder>(){

    interface ICard{
        fun onCardClick(item : Producto)
    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val tvProductoNombre: TextView = itemView.findViewById(R.id.tvProductoNombre)
        val tvProductoDescripcion: TextView = itemView.findViewById(R.id.tvProductoDescripcion)
        val tvProductoPrecio: TextView = itemView.findViewById(R.id.tvProductoPrecio)

        init{
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            iCard.onCardClick(items[adapterPosition])
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View =LayoutInflater.from(parent.context).inflate(R.layout.item_producto,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvProductoNombre.text = item.nom_producto
        holder.tvProductoDescripcion.text = item.des_producto
        holder.tvProductoPrecio.text = String.format("%.2f",item.preciouni_producto)
    }

    fun update(newItems : List<Producto>){
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }
}