package com.example.restaurante.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.data.room.entity.Usuario
import com.example.restaurante.databinding.ActivityLoginBinding
import com.example.restaurante.domain.viewmodel.UsuarioViewModel
import com.example.restaurante.presentation.main.MainTabActivity
import com.example.restaurante.presentation.registro.RegistroActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel : UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(SharedPreferences.getPrefUsuario(applicationContext) != null){
            startActivity(Intent(this,MainTabActivity::class.java))
            finish()
        }
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }

    private fun initValues(){
        viewModel = ViewModelProvider(this)[UsuarioViewModel::class.java]

        binding.btnIngresar.setOnClickListener{
            if (!validarFormulario())
                return@setOnClickListener
            viewModel.loginUsuario(loginForm())
        }

        binding.btnRegistrar.setOnClickListener {
            startActivity(Intent(this,RegistroActivity::class.java))
        }
    }

    private fun initObservers() {
        viewModel.login.observe(this){
            if(it.id_usuario > 0){
                SharedPreferences.setPrefUsuario(applicationContext,it)
                startActivity(Intent(this,MainTabActivity::class.java))
                finish()
            }
            else
                Toast.makeText(this,"Credenciales incorrectas",Toast.LENGTH_LONG).show()
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

    private fun loginForm(): Usuario {
        var user = binding.etUsuario.text.toString()
        var pass = binding.etPassword.text.toString()
        return Usuario(email_usuario = user, password_usuario = pass)
    }
}