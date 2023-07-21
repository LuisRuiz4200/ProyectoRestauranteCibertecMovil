package com.example.restaurante.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.databinding.ActivityLoginBinding
import com.example.restaurante.domain.entity.Usuario
import com.example.restaurante.presentation.catalogo.ListProductosActivity
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
            database = BDPolleria.getInstancia(this)
            /* Crear usuario */
            var obj = Usuario()
            obj.id_usuario = 1
            obj.email_usuario = "user"
            obj.password_usuario = "123"
            database.usuarioDao().insertUsuario(obj)

            var email = binding.etUsuario.text.toString()
            var pass = binding.etPassword.text.toString()
            var usuario = database.usuarioDao().getUsuarioByEmailAndPass(email, pass)
            if(usuario != null){
                SharedPreferences.setPrefUsuario(applicationContext,usuario)
                startActivity(Intent(this,ListProductosActivity::class.java))
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