package com.example.restaurante.presentation.perfil.MisTarjetas.AgregarTarjeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.data.room.entity.Tarjeta
import com.example.restaurante.data.room.entity.Usuario
import com.example.restaurante.databinding.ActivityAgregarTarjetaBinding
import com.example.restaurante.domain.viewmodel.TarjetaViewModel
import com.google.android.material.textfield.TextInputLayout.END_ICON_PASSWORD_TOGGLE
import java.util.Calendar
import java.util.regex.Pattern

class AgregarTarjetaActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAgregarTarjetaBinding
    private lateinit var viewModelTarjeta: TarjetaViewModel
    private val usuario = Usuario()
    private var yearSystem = Calendar.getInstance().get(Calendar.YEAR)
    private var mothSystem = Calendar.getInstance().get(Calendar.MONTH)


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
            val formatNroTarjeta = removeSpaces(binding.etNroTarjeta.text.toString().trim())
            val anio = "20${binding.etFecha.text.toString().substring(3,5)}"
            val fecha = binding.etFecha.text.toString().trim().replaceRange(3,5,anio)

            tarjeta.numero_tarjeta = formatNroTarjeta
            tarjeta.cvv_tarjeta = binding.etCvv.text.toString().trim()
            tarjeta.fecha_tarjeta = fecha
            tarjeta.nombre_tarjeta = binding.etNombre.text.toString().trim()
            tarjeta.id_usuario = usuario.id_usuario
            viewModelTarjeta.saveTarjeta(tarjeta)
        }

        binding.etNroTarjeta.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val inputText = s?.toString() ?: ""

                // Si la longitud del contenido supera el límite, no permitir más caracteres
                if (inputText.length > 25) {
                    binding.etNroTarjeta.setText(inputText.substring(0, 25))
                    binding.etNroTarjeta.setSelection(25)
                    return
                }

                // Formatear y establecer el texto con los espacios
                val formattedText = formatCardNumberOnInput(inputText)
                if (formattedText != s?.toString()) {
                    binding.etNroTarjeta.setText(formattedText)
                    binding.etNroTarjeta.setSelection(formattedText.length)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.etCvv.setOnKeyListener { v, keyCode, event ->
            if(event.action == KeyEvent.ACTION_UP) {
                val inputText = binding.etCvv.text?.toString()?: ""
                if(inputText.length > 3){
                    val newInputText = inputText.dropLast(1)
                    binding.etCvv.setText(newInputText)
                    binding.etNroTarjeta.setSelection(newInputText.length)
                }
            }
            false
        }

        binding.etFecha.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val inputText = s?.toString() ?: ""

                // Si la longitud del contenido supera el límite, no permitir más caracteres
                if (inputText.length > 5) {
                    binding.etFecha.setText(inputText.substring(0, 5))
                    binding.etFecha.setSelection(5)
                    return
                }

                // Formatear y establecer el texto con el formato MM/AA
                val formattedText = formatCardDateOnInput(inputText)
                if (formattedText != s?.toString()) {
                    binding.etFecha.setText(formattedText)
                    binding.etFecha.setSelection(formattedText.length)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun initObservers(){
        viewModelTarjeta.saveTarjeta.observe(this){
            mensajeGuardar(it)
        }
    }

    private fun formatCardNumberOnInput(input: String): String {
        val trimmed = input.replace(" - ", "")
        return trimmed.chunked(4).joinToString(" - ") { it }
    }

    private fun removeSpaces(cardNumberWithSpaces: String): String {
        return cardNumberWithSpaces.replace(" - ", "")
    }

    private fun formatCardDateOnInput(input: String): String {
        val trimmed = input.replace("/", "")
        return trimmed.chunked(2).joinToString("/") { it }
    }

    private fun invalidForm() : Boolean{
        //Nro de Tarjeta
        if(binding.etNroTarjeta.text.toString().trim().isEmpty()){
            Toast.makeText(this, "Ingrese la número de tarjeta", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etNroTarjeta.text.toString().trim().length != 25){
            Toast.makeText(this, "Número inválido (12 caracteres)", Toast.LENGTH_LONG).show()
            return true
        }

        //Nro de CVC
        if(binding.etCvv.text.toString().trim().isEmpty()){
            Toast.makeText(this, "Ingrese su CVV", Toast.LENGTH_LONG).show()
            return true
        }
        if (binding.etCvv.text.toString().trim().length != 3) {
            Toast.makeText(this, "Formato de CVV inválido (3 caracteres)", Toast.LENGTH_LONG).show()
            return true
        }

        //Fecha
        if(binding.etFecha.text.toString().trim().isEmpty()){
            Toast.makeText(this, "Ingrese la fecha", Toast.LENGTH_LONG).show()
            return true
        }
        val pattern: Pattern = Pattern.compile("^(0[1-9]|1[0-2])/(\\d{2})\$")
        if (!pattern.matcher(binding.etFecha.text.toString().trim()).matches()) {
            Toast.makeText(this, "Formato de fecha inválido (MM/AA)", Toast.LENGTH_LONG).show()
            return true
        }
        val mes = binding.etFecha.text.toString().substring(0,2).toInt()
        println("$mes, $mothSystem")
        val anio= "20${binding.etFecha.text.toString().substring(3,5)}".toInt()
        println("$anio, $yearSystem")
        if(anio < yearSystem || (anio == yearSystem && mes < mothSystem + 1)){
            Toast.makeText(this, "Ingrese una fecha mayor igual a la actual", Toast.LENGTH_LONG).show()
            return true
        }

        //Nombre
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