package com.example.restaurante.presentation.perfil.CambiarContrasena

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.data.room.entity.Usuario
import com.example.restaurante.databinding.ActivityCambiarContrasenaBinding
import com.example.restaurante.domain.viewmodel.UsuarioViewModel

class CambiarContrasenaActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCambiarContrasenaBinding
    private lateinit var viewModel: UsuarioViewModel
    private lateinit var usuario : Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCambiarContrasenaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }

    private fun initValues() {
        viewModel = ViewModelProvider(this)[UsuarioViewModel::class.java]

        binding.btnCambiar.setOnClickListener {
            // Validar formulario
            if(invalidForm())
                return@setOnClickListener
            if(invalidPass())
                return@setOnClickListener
            if(invalidActualPass())
                return@setOnClickListener
            // Obtener datos
            usuario = Usuario()
            usuario.password_usuario = binding.etNewPassword.text.toString().trim()
            // Enviar cambios a la API
            usuario.id_usuario = SharedPreferences.getPrefUsuario(this)!!.id_usuario
            viewModel.changePass(usuario)
        }

        binding.btnBackArrow.setOnClickListener {
            finish()
        }
    }

    private fun initObservers() {
        viewModel.changePass.observe(this){
            mensajeGuardar(it)
        }
    }

    private fun invalidForm() : Boolean{
        if(binding.etPassword.text.toString().trim().isEmpty()){
            Toast.makeText(this, "Ingrese su contraseña actual.", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etNewPassword.text.toString().trim().isEmpty()){
            Toast.makeText(this, "Ingrese su nueva contraseña.", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etConfirmNewPassword.text.toString().trim().isEmpty()){
            Toast.makeText(this, "Ingrese confirme la nueva contraseña.", Toast.LENGTH_LONG).show()
            return true
        }
        return false
    }

    private fun invalidPass() : Boolean{
        val pass = binding.etNewPassword.text.toString().trim()
        val conf = binding.etConfirmNewPassword.text.toString().trim()
        if(pass != conf){
            Toast.makeText(this, "La nueva contraseña no coincide", Toast.LENGTH_LONG).show()
            return true
        }
        return false
    }

    private fun invalidActualPass() : Boolean{
        val usuarioSession = SharedPreferences.getPrefUsuario(this)!!
        if(usuarioSession.password_usuario != binding.etPassword.text.toString().trim()){
            Toast.makeText(this, "La contraseña actual es incorrecta.", Toast.LENGTH_LONG).show()
            return true
        }
        return false
    }

    private fun mensajeGuardar(it: String){
        AlertDialog.Builder(this)
            .setTitle("Cambiar contraseña")
            .setMessage(it)
            .setNeutralButton("Ok"){ _, _->
                finish()
            }
            .setCancelable(false)
            .show()
    }
}