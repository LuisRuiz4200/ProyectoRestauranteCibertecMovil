package com.example.restaurante.presentation.perfil.MisDirecciones.EditarDireccion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.restaurante.data.room.entity.Direccion
import com.example.restaurante.databinding.ActivityEditarDireccionBinding
import com.example.restaurante.domain.viewmodel.DireccionViewModel

class EditarDireccionActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditarDireccionBinding
    private lateinit var viewModelDireccion: DireccionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarDireccionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }

    private fun initValues() {
        viewModelDireccion = ViewModelProvider(this)[DireccionViewModel::class.java]
        val idItem = intent.getIntExtra("id", 0)
        setForm()

        binding.btnEditar.setOnClickListener {
            if(invalidForm())
                return@setOnClickListener

            val direccion = Direccion()
            direccion.id_direntrega = idItem
            direccion.nombre_direntrega = binding.etNombre.text.toString()
            direccion.des_direntrega = binding.etDescripcion.text.toString()
            direccion.detalle_direntrega = binding.etDetalle.text.toString()
            viewModelDireccion.updateDireccion(direccion)
        }
    }

    private fun initObservers(){
        viewModelDireccion.updateDireccion.observe(this){
            mensajeGuardar(it)
        }
    }

    private fun setForm() {
        val nomItem = intent.getStringExtra("nombre")!!
        val desItem = intent.getStringExtra("descripcion")!!
        val detItem = intent.getStringExtra("detalle")
        binding.etNombre.setText(nomItem)
        binding.etDescripcion.setText(desItem)
        binding.etDetalle.setText(detItem)
    }

    private fun invalidForm() : Boolean{
        if(binding.etNombre.text.toString().trim().isEmpty()){
            Toast.makeText(this, "Ingrese un Nombre o Alias para su dirección", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etDescripcion.text.toString().trim().isEmpty()){
            Toast.makeText(this, "Ingrese la dirección", Toast.LENGTH_LONG).show()
            return true
        }
        return false
    }

    private fun mensajeGuardar(msg : String){
        AlertDialog.Builder(this)
            .setTitle("Editar Direccion")
            .setMessage(msg)
            .setNeutralButton("Ok"){ _, _->
                finish()
            }
            .setCancelable(false)
            .show()
    }
}