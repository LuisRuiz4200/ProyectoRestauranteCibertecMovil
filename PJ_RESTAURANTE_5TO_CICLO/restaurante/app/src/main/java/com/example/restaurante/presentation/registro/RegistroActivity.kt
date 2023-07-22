package com.example.restaurante.presentation.registro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.restaurante.R
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.data.room.entity.Usuario
import com.example.restaurante.databinding.ActivityRegistroBinding
import kotlinx.android.synthetic.main.activity_registro.view.etTelefono

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegistroBinding
    private lateinit var database : BDPolleria
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues(){
        binding.btnRegistrar.setOnClickListener {
            if(invalidForm())
                return@setOnClickListener
            if(invalidPass())
                return@setOnClickListener
            if(invalidCheckBox())
                return@setOnClickListener
            database = BDPolleria.getInstancia(this)
            var usuario = Usuario()
            usuario.nom_usuario = binding.etNombreCompleto.text.toString()
            usuario.tel_usuario = binding.etTelefono.text.toString()
            usuario.email_usuario =  binding.etEmail.text.toString()
            usuario.password_usuario = binding.etContrasena.text.toString()
            database.usuarioDao().insertUsuario(usuario)
            mensajeGuardar()
        }
    }

    private fun invalidForm() : Boolean{
        if(binding.etNombreCompleto.text.toString().isNullOrEmpty()){
            Toast.makeText(this, "Ingrese su nombre completo.", Toast.LENGTH_LONG).show()
            return true
        }
        if(binding.etEmail.etTelefono.toString().isNullOrEmpty()){
            Toast.makeText(this, "Ingrese su telefono.", Toast.LENGTH_LONG).show()
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
        var pass = binding.etContrasena.text.toString()
        var conf = binding.etConfirmarContrasena.text.toString()
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