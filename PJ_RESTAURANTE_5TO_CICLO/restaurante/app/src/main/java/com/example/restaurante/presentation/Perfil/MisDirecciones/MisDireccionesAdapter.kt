package com.example.restaurante.presentation.Perfil.MisDirecciones

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurante.R
import com.example.restaurante.data.room.entity.Producto

class MisDireccionesAdapter(var items: MutableList<Producto>) : RecyclerView.Adapter<MisDireccionesAdapter.ViewHolder>() {
    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvProducto: TextView = itemView.findViewById(R.id.tvProducto)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
        val tvPrecio: TextView = itemView.findViewById(R.id.tvPrecio)
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
        holder.tvProducto.text=item.nom_producto
        holder.tvDescripcion.text=item.des_producto
        holder.tvPrecio.text=item.preciouni_producto.toString()
    }
}