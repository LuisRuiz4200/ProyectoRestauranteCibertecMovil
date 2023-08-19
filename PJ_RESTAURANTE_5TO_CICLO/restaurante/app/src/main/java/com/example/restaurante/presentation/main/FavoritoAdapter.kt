package com.example.restaurante.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restaurante.R
import com.example.restaurante.data.room.entity.Favorito

class FavoritoAdapter
    (var items : MutableList<Favorito>)
    : RecyclerView.Adapter<FavoritoAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvImg: ImageFilterView = itemView.findViewById(R.id.rvFavoritoImg)
        val tvNombre: TextView = itemView.findViewById(R.id.rvFavoritoNombre)
        val tvPrecio: TextView = itemView.findViewById(R.id.rvFavoritoPrecio)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_favorito, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        Glide.with(holder.itemView.context).load(item.producto.imagen_producto).into(holder.tvImg)
        holder.tvNombre.text = item.producto.nom_producto
        holder.tvPrecio.text = item.producto.preciouni_producto.toString()
    }

    fun update(newItems : List<Favorito>){
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }
}