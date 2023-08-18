package com.example.restaurante.presentation.perfil.MisPedidos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurante.R
import com.example.restaurante.data.room.entity.Pedido
import java.text.SimpleDateFormat

class MisPedidosAdapter
    (var items: MutableList<Pedido>, var iCard: ICard)
    : RecyclerView.Adapter<MisPedidosAdapter.ViewHolder>() {

    interface ICard{
        fun onCardClick(item: Pedido)
    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val tvPedidoNumero: TextView = itemView.findViewById(R.id.tvPedidoNumero)
        val tvPedidoEstado: TextView = itemView.findViewById(R.id.tvPedidoEstado)
        val tvPedidoDescripcion: TextView = itemView.findViewById(R.id.tvPedidoDescripcion)
        val tvPedidoFecha: TextView = itemView.findViewById(R.id.tvPedidoFecha)

        init {

        }
        override fun onClick(v: View?) {
            iCard.onCardClick(items[adapterPosition])
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_pedido,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        if(item.id_pedido > 0){
            val pedidoNumero = "Pedido #${item.id_pedido}"
            holder.tvPedidoNumero.text = pedidoNumero
            holder.tvPedidoEstado.text = item.estado_pedido
            println(item.estado_pedido)
            when(item.estado_pedido.trim()){ //TODO no funciona correctamente
                "Entregado" -> {
                    val colorList = ContextCompat.getColorStateList(holder.itemView.context, R.color.estado_entregado)
                    holder.tvPedidoEstado.backgroundTintList = colorList
                }
                "En camino" -> {
                    val colorList = ContextCompat.getColorStateList(holder.itemView.context, R.color.estado_pendiente)
                    holder.tvPedidoEstado.backgroundTintList = colorList
                }
                "Pendiente" -> {
                    val colorList = ContextCompat.getColorStateList(holder.itemView.context, R.color.estado_cancelado)
                    holder.tvPedidoEstado.backgroundTintList = colorList
                }
            }

            val desc = "S/${item.monto_compra} - ${item.nombre_direntrega}"
            holder.tvPedidoDescripcion.text = desc

            // Formato del string de fecha y hora original
            val originalFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val timeFormat = SimpleDateFormat("HH:mm")
            // Parsear el string a un objeto Date
            val date = originalFormat.parse(item.fechaAct_pedido)
            val formattedDate = dateFormat.format(date)
            val formattedTime = timeFormat.format(date)
            val fecha = "$formattedDate - $formattedTime"
            holder.tvPedidoFecha.text = fecha
        }
    }

    fun update(newItems : List<Pedido>){
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }
}