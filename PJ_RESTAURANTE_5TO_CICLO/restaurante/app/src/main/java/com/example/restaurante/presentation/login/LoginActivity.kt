package com.example.restaurante.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.data.room.entity.Usuario
import com.example.restaurante.databinding.ActivityLoginBinding
import com.example.restaurante.presentation.catalogo.ListProductosActivity
import com.example.restaurante.presentation.main.MainTabActivity
import com.example.restaurante.presentation.registro.RegistroActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var database : BDPolleria

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(SharedPreferences.getPrefUsuario(applicationContext) != null){
            startActivity(Intent(this,MainTabActivity::class.java))
            finish()
        }
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        usuarioTemporal()
        initValues()
    }

    private fun initValues(){
        binding.btnIngresar.setOnClickListener{
            if (!validarFormulario())
                return@setOnClickListener

            var user = binding.etUsuario.text.toString()
            var pass = binding.etPassword.text.toString()
            var usuario = database.usuarioDao().getUsuarioByUserAndPass(user, pass)

            if(usuario != null){
                SharedPreferences.setPrefUsuario(applicationContext,usuario)
                startActivity(Intent(this,MainTabActivity::class.java))
                finish()
            }
            else{
                Toast.makeText(this,"Credenciales incorrectas",Toast.LENGTH_LONG).show()
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

    private fun usuarioTemporal(){
        database = BDPolleria.getInstancia(this)
        /* Crear usuario */
        var obj = Usuario()
        obj.id_usuario = 1
        obj.nom_usuario = "Pablito Backyardigans"
        obj.email_usuario = "test@test.com"
        obj.password_usuario = "123"
        obj.tel_usuario = "999999999"
        database.usuarioDao().insertUsuario(obj)
    }
}