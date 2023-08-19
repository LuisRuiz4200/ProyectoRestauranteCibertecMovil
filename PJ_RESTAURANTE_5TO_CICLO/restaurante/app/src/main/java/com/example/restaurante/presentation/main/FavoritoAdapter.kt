package com.example.restaurante.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restaurante.R
import com.example.restaurante.data.room.entity.Favorito
import com.example.restaurante.domain.viewmodel.FavoritoViewModel

class FavoritoAdapter
    (var items : MutableList<Favorito>, var iCard: ICard, var viewModel : FavoritoViewModel)
    : RecyclerView.Adapter<FavoritoAdapter.ViewHolder>(){

    interface ICard{
        fun onCardClick(item : Favorito)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), OnClickListener {
        val tvImg: ImageFilterView = itemView.findViewById(R.id.rvFavoritoImg)
        val tvNombre: TextView = itemView.findViewById(R.id.rvFavoritoNombre)
        val tvPrecio: TextView = itemView.findViewById(R.id.rvFavoritoPrecio)
        val imgTrash: ImageFilterView = itemView.findViewById(R.id.rvFavoritoEliminar)

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            iCard.onCardClick(items[adapterPosition])
        }

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
        holder.tvPrecio.text = String.format("%.2f",item.producto.preciouni_producto)

        holder.imgTrash.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Eliminar Favorito")
                .setMessage("¿Esta seguro de eliminar ${item.producto.nom_producto} de sus favoritos?")
                .setPositiveButton("Sí"){ _, _ ->
                    viewModel.deleteFavorito(item)
                }
                .setNegativeButton("No"){ _, _ ->
                    return@setNegativeButton
                }
                .setCancelable(true)
                .show()
        }
    }

    fun update(newItems : List<Favorito>){
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }
}