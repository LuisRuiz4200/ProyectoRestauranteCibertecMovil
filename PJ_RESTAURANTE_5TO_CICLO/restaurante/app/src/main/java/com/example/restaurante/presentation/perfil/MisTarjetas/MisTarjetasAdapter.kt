package com.example.restaurante.presentation.perfil.MisTarjetas

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurante.R
import com.example.restaurante.data.room.entity.Tarjeta
import com.example.restaurante.presentation.perfil.MisDirecciones.EditarDireccion.EditarDireccionActivity
import kotlinx.android.synthetic.main.item_direccion.view.btnDireccionAction

class MisTarjetasAdapter(var items: MutableList<Tarjeta>, var buyingMode: Int, var iCard: ICard)
    : RecyclerView.Adapter<MisTarjetasAdapter.ViewHolder>() {

    interface ICard{
        fun onCardClick(item : Tarjeta)
    }
    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val tvTarjetaNombre: TextView = itemView.findViewById(R.id.tvTarjetaNombre)
        val tvTarjetaNumero: TextView = itemView.findViewById(R.id.tvTarjetaNumero)
        val tvTarjetaFecha: TextView = itemView.findViewById(R.id.tvTarjetaFecha)
        init {
            if(buyingMode == 1)
                itemView.setOnClickListener(this)

//            itemView.btnDireccionAction.setOnClickListener { TODO
//                val item = items[adapterPosition]
//                itemView.context.startActivity(
//                    Intent(itemView.context, EditarDireccionActivity::class.java).apply {
//                        putExtra("id", item.id_direntrega)
//                    })
//            }
        }
        override fun onClick(v: View?) {
            iCard.onCardClick(items[adapterPosition])
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tarjeta,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvTarjetaNombre.text=item.nombre_tarjeta
        holder.tvTarjetaFecha.text=item.fecha_tarjeta
        holder.tvTarjetaNumero.text = formatCard(item.numero_tarjeta)
    }

    fun update(newItems : List<Tarjeta>){
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }

    private fun formatCard(number : String) : String{
        var showNum = number
        showNum = showNum.replaceRange(0,12,"************")
        showNum = showNum.chunked(4).joinToString(" - ")
        return showNum
    }
}