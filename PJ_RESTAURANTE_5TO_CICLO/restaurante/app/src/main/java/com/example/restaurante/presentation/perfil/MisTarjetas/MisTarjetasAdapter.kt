package com.example.restaurante.presentation.perfil.MisTarjetas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurante.R
import com.example.restaurante.data.room.entity.Tarjeta
import com.example.restaurante.domain.viewmodel.TarjetaViewModel
import kotlinx.android.synthetic.main.item_tarjeta.view.btnTarjetaEliminar

class MisTarjetasAdapter
    (var items: MutableList<Tarjeta>, var buyingMode: Int, var iCard: ICard,
     private val viewModel: TarjetaViewModel)
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

        holder.itemView.btnTarjetaEliminar.setOnClickListener {
            println("Holi Click")
            val tarjeta = items[holder.adapterPosition]
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Eliminar Tarjeta")
                .setMessage("¿Esta seguro de eliminar ${tarjeta.nombre_tarjeta} como su tarjeta?")
                .setPositiveButton("Sí"){ _, _ ->
                    println("Holi Sí")

                    viewModel.deleteTarjeta(tarjeta)
                }
                .setNegativeButton("No"){ _, _ ->
                    return@setNegativeButton
                }
                .setCancelable(true)
                .show()
        }
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