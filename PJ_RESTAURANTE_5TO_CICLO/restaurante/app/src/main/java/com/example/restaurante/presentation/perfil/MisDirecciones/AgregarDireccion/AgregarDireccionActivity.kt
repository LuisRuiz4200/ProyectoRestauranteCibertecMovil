package com.example.restaurante.presentation.perfil.MisDirecciones.AgregarDireccion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.data.room.entity.Direccion
import com.example.restaurante.data.room.entity.Usuario
import com.example.restaurante.databinding.ActivityAgregarDireccionBinding
import com.example.restaurante.domain.viewmodel.DireccionViewModel

class AgregarDireccionActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAgregarDireccionBinding
    private lateinit var viewModelDireccion : DireccionViewModel
    private var usuario = Usuario()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarDireccionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }

    private fun initValues() {
        viewModelDireccion = ViewModelProvider(this)[DireccionViewModel::class.java]

        binding.btnAgregar.setOnClickListener {
            if(invalidForm())
                return@setOnClickListener

            usuario.id_usuario = SharedPreferences.getPrefUsuario(this)!!.id_usuario
            val direccion = Direccion()
            direccion.nombre_direntrega = binding.etNombre.text.toString()
            direccion.des_direntrega = binding.etDescripcion.text.toString()
            direccion.detalle_direntrega = binding.etDetalle.text.toString()
            direccion.id_usuario = usuario.id_usuario
            viewModelDireccion.saveDireccion(direccion)
        }
    }

    private fun initObservers(){
        viewModelDireccion.saveDireccion.observe(this){
            mensajeGuardar(it)
        }
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
            .setTitle("Nueva Direccion")
            .setMessage(msg)
            .setNeutralButton("Ok"){ _, _->
                finish()
            }
            .setCancelable(false)
            .show()
    }
}