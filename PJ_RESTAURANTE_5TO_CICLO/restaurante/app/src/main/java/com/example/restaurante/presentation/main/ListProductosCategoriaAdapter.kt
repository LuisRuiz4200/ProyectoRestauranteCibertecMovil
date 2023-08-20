package com.example.restaurante.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurante.R
import com.example.restaurante.data.room.entity.Categoria

class ListProductosCategoriaAdapter
    (var items : MutableList<Categoria>, var iCard: ICard)
    : RecyclerView.Adapter<ListProductosCategoriaAdapter.ViewHolder>(){

    interface ICard {
        fun onCardClick(item : Categoria)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val tvCategoriaNombre : TextView = itemView.findViewById(R.id.tvCategoriaNombre)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            iCard.onCardClick(items[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_categoria,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvCategoriaNombre.text = item.des_categoria_producto
    }

    fun update(newItems : List<Categoria>){
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }

}