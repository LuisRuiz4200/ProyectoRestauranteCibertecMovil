package com.example.restaurante.presentation.perfil.MisTarjetas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurante.R
import com.example.restaurante.data.room.entity.Producto

class MisTarjetasAdapter(var items: MutableList<Producto>) : RecyclerView.Adapter<MisTarjetasAdapter.ViewHolder>() {
    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvNombre: TextView = itemView.findViewById(R.id.tvDireccionNombre)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDireccionDescripcion)
        val tvDetalle: TextView = itemView.findViewById(R.id.tvDireccionDetalle)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_direccion,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvNombre.text=item.nom_producto
        holder.tvDescripcion.text=item.des_producto
        holder.tvDetalle.text=item.preciouni_producto.toString()
    }
}