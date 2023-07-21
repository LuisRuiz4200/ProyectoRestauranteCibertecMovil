package com.example.restaurante.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.databinding.ActivityLoginBinding
import com.example.restaurante.domain.entity.Usuario
import com.example.restaurante.presentation.catalogo.ListProductosActivity
import com.example.restaurante.presentation.home.HomeActivity
import com.example.restaurante.presentation.registro.RegistroActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues(){
        binding.btnIngresar.setOnClickListener{
            if (!validarFormulario())
                return@setOnClickListener
            var usuario = Usuario()
            usuario.idUsuario = 1
            usuario.nombreCompleto = binding.etUsuario.text.toString()
            usuario.login = binding.etPassword.text.toString()
            usuario.edad = 23
            if(SharedPreferences.setPrefUsuario(applicationContext,usuario)==1){
                startActivity(
                    Intent(this,HomeActivity::class.java)
                )
                finish()
            }
            else{
                Toast.makeText(this,"Sucedio un error",Toast.LENGTH_LONG).show()
            }
        }

        binding.btnRegistrar.setOnClickListener {
            startActivity(Intent(this,RegistroActivity::class.java))
        }
    }

    private fun validarFormulario() : Boolean {
        if(binding.etUsuario.text.toString().isNullOrEmpty()){
            Toast.makeText(this, "Debe ingresar un usuario.", Toast.LENGTH_LONG).show()
            return false
        }
        if(binding.etPassword.text.toString().isNullOrEmpty()){
            Toast.makeText(this, "Debe ingresar una contraseña.", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}