package com.example.restaurante.presentation.perfil.MisDirecciones

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurante.R
import com.example.restaurante.data.room.entity.Direccion
import com.example.restaurante.domain.viewmodel.DireccionViewModel
import com.example.restaurante.presentation.perfil.MisDirecciones.EditarDireccion.EditarDireccionActivity
import kotlinx.android.synthetic.main.item_direccion.view.btnDireccionAction
import kotlinx.android.synthetic.main.item_direccion.view.rvDireccionTrash

class MisDireccionesAdapter
    (var items: MutableList<Direccion>, var iCard: ICard, var buyingMode: Int, var viewModel : DireccionViewModel)
    : RecyclerView.Adapter<MisDireccionesAdapter.ViewHolder>() {

    interface ICard{
        fun onCardClick(item : Direccion)
    }
    inner class ViewHolder (itemView: View)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val tvDireccionNombre: TextView = itemView.findViewById(R.id.tvDireccionNombre)
        val tvDireccionDescripcion: TextView = itemView.findViewById(R.id.tvDireccionDescripcion)
        val tvDireccionDetalle: TextView = itemView.findViewById(R.id.tvDireccionDetalle)
        init{
            if(buyingMode == 1)
                itemView.setOnClickListener(this)

            itemView.btnDireccionAction.setOnClickListener {
                val item = items[adapterPosition]
                itemView.context.startActivity(
                    Intent(itemView.context, EditarDireccionActivity::class.java).apply {
                        putExtra("id", item.id_direntrega)
                        putExtra("nombre", item.nombre_direntrega)
                        putExtra("descripcion", item.des_direntrega)
                        putExtra("detalle", item.detalle_direntrega)
                    })
            }
        }

        override fun onClick(v : View?){
            iCard.onCardClick(items[adapterPosition])
        }
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
        holder.tvDireccionNombre.text = item.nombre_direntrega
        holder.tvDireccionDescripcion.text = item.des_direntrega
        holder.tvDireccionDetalle.text = item.detalle_direntrega

        holder.itemView.rvDireccionTrash.setOnClickListener {
            val direccion = items[holder.adapterPosition]
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Eliminar Direccion")
                .setMessage("¿Esta seguro de eliminar ${direccion.nombre_direntrega} como su dirección?")
                .setPositiveButton("Sí"){ _, _ ->
                    viewModel.deleteDireccion(item.id_direntrega)
                }
                .setNegativeButton("No"){ _, _ ->
                    return@setNegativeButton
                }
                .setCancelable(true)
                .show()
        }
    }

    fun update(newItems : List<Direccion>){
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }
}