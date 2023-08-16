package com.example.restaurante.presentation.perfil.EditarPerfil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.data.room.entity.Usuario
import com.example.restaurante.databinding.ActivityEditarPerfilBinding
import com.example.restaurante.domain.viewmodel.UsuarioViewModel
import com.example.restaurante.presentation.perfil.CambiarContrasena.CambiarContrasenaActivity

class EditarPerfilActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditarPerfilBinding
    private lateinit var viewModel: UsuarioViewModel
    private lateinit var usuario : Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }

    private fun initValues() {
        viewModel = ViewModelProvider(this)[UsuarioViewModel::class.java]
        // Mostrar datos desde Usuario en Sesión
        setProfile()

        // Cambiar Contraseña
        binding.btnCambiarContrasena.setOnClickListener {
            startActivity(Intent(this,CambiarContrasenaActivity::class.java))
        }

        // Editar datos
        binding.btnGuardar.setOnClickListener{
            if(invalidForm())
                return@setOnClickListener
            // Capturar datos del formulario
            usuario = Usuario()
            usuario.nom_usuario = binding.etNombres.text.toString().trim()
            usuario.ape_usuario = binding.etApellidos.text.toString().trim()
            usuario.email_usuario = binding.etEmail.text.toString().trim()
            usuario.cel_usuario = binding.etTelefono.text.toString().trim()
            usuario.id_usuario = SharedPreferences.getPrefUsuario(this)!!.id_usuario
            // Validar existencia, luego el observador registrará
            viewModel.validUsuario(usuario)
        }

        binding.btnBackArrow.setOnClickListener {
            finish()
        }
    }

    private fun initObservers(){
        viewModel.validUsuario.observe(this){
            if(it == "Valid")
                viewModel.updateUsuario(usuario)
            else
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        viewModel.updateUsuario.observe(this){
            editarPreference()
            mensajeGuardar()
        }
    }

    private fun setProfile() {
        val userSession = SharedPreferences.getPrefUsuario(this)!!
        binding.etNombres.setText(userSession.nom_usuario.trim())
        binding.etApellidos.setText(userSession.ape_usuario.trim())
        binding.etEmail.setText(userSession.email_usuario.trim())
        binding.etTelefono.setText(userSession.cel_usuario.trim())
    }

    private fun invalidForm() : Boolean{
        if(binding.etNombres.text.toString().trim().isEmpty()){
            Toast.makeText(this, "Ingrese sus nombres.", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etApellidos.text.toString().trim().isEmpty()){
            Toast.makeText(this, "Ingrese sus apellidos.", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etTelefono.text.toString().trim().isEmpty()){
            Toast.makeText(this, "Ingrese su telefono.", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etTelefono.text.toString().trim().length != 9){
            Toast.makeText(this, "Ingrese un telefono válido (9 caracteres).", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etEmail.text.toString().trim().isEmpty()){
            Toast.makeText(this, "Ingrese su email.", Toast.LENGTH_LONG).show()
            return true
        }
        return false
    }

    private fun editarPreference() {
        val usuarioSession = SharedPreferences.getPrefUsuario(this)!!
        usuarioSession.nom_usuario = usuario.nom_usuario
        usuarioSession.ape_usuario = usuario.ape_usuario
        usuarioSession.email_usuario = usuario.email_usuario
        usuarioSession.cel_usuario = usuario.cel_usuario
        SharedPreferences.setPrefUsuario(this, usuarioSession)
    }


    private fun mensajeGuardar(){
        AlertDialog.Builder(this)
            .setTitle("Editar Perfil")
            .setMessage("Datos actualizados con éxito.")
            .setNeutralButton("Ok"){ _, _->
                finish()
            }
            .setCancelable(false)
            .show()
    }
}