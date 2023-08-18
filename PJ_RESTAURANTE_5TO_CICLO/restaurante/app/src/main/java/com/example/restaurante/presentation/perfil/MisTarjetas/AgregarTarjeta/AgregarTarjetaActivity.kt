package com.example.restaurante.presentation.perfil.MisTarjetas.AgregarTarjeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.data.room.entity.Tarjeta
import com.example.restaurante.data.room.entity.Usuario
import com.example.restaurante.databinding.ActivityAgregarTarjetaBinding
import com.example.restaurante.domain.viewmodel.TarjetaViewModel
import com.google.android.material.textfield.TextInputLayout.END_ICON_PASSWORD_TOGGLE
import java.util.regex.Pattern

class AgregarTarjetaActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAgregarTarjetaBinding
    private lateinit var viewModelTarjeta: TarjetaViewModel
    private val usuario = Usuario()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarTarjetaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }

    private fun initValues() {
        viewModelTarjeta = ViewModelProvider(this)[TarjetaViewModel::class.java]
        binding.tilCvv.endIconMode = END_ICON_PASSWORD_TOGGLE

        binding.btnAgregar.setOnClickListener {
            if(invalidForm())
                return@setOnClickListener

            usuario.id_usuario = SharedPreferences.getPrefUsuario(this)!!.id_usuario
            val tarjeta = Tarjeta()
            tarjeta.numero_tarjeta = binding.etNroTarjeta.text.toString().trim()
            tarjeta.cvv_tarjeta = binding.etCvv.text.toString().trim()
            tarjeta.fecha_tarjeta = binding.etFecha.text.toString().trim()
            tarjeta.nombre_tarjeta = binding.etNombre.text.toString().trim()
            tarjeta.id_usuario = usuario.id_usuario
            viewModelTarjeta.saveTarjeta(tarjeta)
        }
    }

    private fun initObservers(){
        viewModelTarjeta.saveTarjeta.observe(this){
            mensajeGuardar(it)
        }
    }

    private fun invalidForm() : Boolean{
        if(binding.etNroTarjeta.text.toString().trim().isEmpty()){
            Toast.makeText(this, "Ingrese la número de tarjeta", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etNroTarjeta.text.toString().trim().length != 12){
            Toast.makeText(this, "Número inválido (12 caracteres)", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etCvv.text.toString().trim().isEmpty()){
            Toast.makeText(this, "Ingrese su CVV", Toast.LENGTH_LONG).show()
            return true
        }
        if (binding.etCvv.text.toString().trim().length != 3) {
            Toast.makeText(this, "Formato de fecha inválido (MM/AA)", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etFecha.text.toString().trim().isEmpty()){
            Toast.makeText(this, "Ingrese la dirección", Toast.LENGTH_LONG).show()
            return true
        }
        val pattern: Pattern = Pattern.compile("^(0[1-9]|1[0-2])/(\\d{2})\$")
        if (!pattern.matcher(binding.etFecha.text.toString().trim()).matches()) {
            Toast.makeText(this, "Formato de fecha inválido (MM/AA)", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etNombre.text.toString().trim().isEmpty()){
            Toast.makeText(this, "Ingrese un Nombre o Alias para su dirección", Toast.LENGTH_LONG).show()
            return true
        }
        return false
    }

    private fun mensajeGuardar(msg : String){
        AlertDialog.Builder(this)
            .setTitle("Nueva Tarjeta")
            .setMessage(msg)
            .setNeutralButton("Ok"){ _, _->
                finish()
            }
            .setCancelable(false)
            .show()
    }
}