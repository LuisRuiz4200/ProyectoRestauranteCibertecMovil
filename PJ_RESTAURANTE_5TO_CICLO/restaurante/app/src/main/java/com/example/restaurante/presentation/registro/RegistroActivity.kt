package com.example.restaurante.presentation.registro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.restaurante.R
import com.example.restaurante.data.room.entity.Usuario
import com.example.restaurante.databinding.ActivityRegistroBinding
import com.example.restaurante.domain.viewmodel.UsuarioViewModel

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegistroBinding
    private lateinit var viewModel: UsuarioViewModel
    private lateinit var usuario : Usuario
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }

    private fun initValues(){
        viewModel = ViewModelProvider(this)[UsuarioViewModel::class.java]

        binding.btnRegistrar.setOnClickListener {
            // Validaciones de formularios
            if(invalidForm())
                return@setOnClickListener
            if(invalidPass())
                return@setOnClickListener
            if(invalidCheckBox())
                return@setOnClickListener

            // Captura de datos
            usuario = Usuario()
            usuario.nom_usuario = binding.etNombres.text.toString()
            usuario.ape_usuario = binding.etApellidos.text.toString()
            usuario.cel_usuario = binding.etTelefono.text.toString()
            usuario.email_usuario =  binding.etEmail.text.toString()
            usuario.password_usuario = binding.etContrasena.text.toString()

            // Corrobrar existencia, donde el observador registrará
            viewModel.validUsuario(usuario)
        }

        binding.tvRegistroIniciarSesion.setOnClickListener {
            finish()
        }
    }

    private fun initObservers(){
        viewModel.validUsuario.observe(this){
            if(it == "Valid")
                viewModel.saveUsuario(usuario)
            else
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        viewModel.saveUsuario.observe(this){
            mensajeGuardar()
        }
    }

    private fun invalidForm() : Boolean{
        if(binding.etNombres.text.toString().isNullOrEmpty()){
            Toast.makeText(this, "Ingrese sus nombres.", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etApellidos.text.toString().isNullOrEmpty()){
            Toast.makeText(this, "Ingrese sus apellidos.", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etTelefono.text.toString().isNullOrEmpty()){
            Toast.makeText(this, "Ingrese su telefono.", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etTelefono.text.toString().length != 9){
            Toast.makeText(this, "Ingrese un telefono válido (9 caracteres).", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etEmail.text.toString().isNullOrEmpty()){
            Toast.makeText(this, "Ingrese su email.", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etContrasena.text.toString().isNullOrEmpty()){
            Toast.makeText(this, "Ingrese su contraseña.", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etConfirmarContrasena.text.toString().isNullOrEmpty()){
            Toast.makeText(this, "Confirme su contraseña.", Toast.LENGTH_LONG).show()
            return true
        }
        return false
    }

    private fun invalidPass() : Boolean{
        val pass = binding.etContrasena.text.toString()
        val conf = binding.etConfirmarContrasena.text.toString()
        if(pass != conf){
            Toast.makeText(this, "Las contraseñas son diferentes", Toast.LENGTH_LONG).show()
            return true
        }
        return false
    }

    private fun invalidCheckBox() : Boolean{
        if(!binding.chkTerminos.isChecked){
            Toast.makeText(this, "Acepte los términos y condiciones", Toast.LENGTH_LONG).show()
            return true
        }
        return false
    }

    private fun mensajeGuardar(){
        AlertDialog.Builder(this)
            .setTitle("Nuevo registro")
            .setMessage("Su registro fue exitoso")
            .setNeutralButton("Ok"){dialog,_->
                finish()
            }
            .setCancelable(false)
            .show()
    }
}