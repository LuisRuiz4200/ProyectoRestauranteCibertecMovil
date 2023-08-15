package com.example.restaurante.presentation.perfil.EditarPerfil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.databinding.ActivityEditarPerfilBinding
import com.example.restaurante.presentation.perfil.CambiarContrasena.CambiarContrasenaActivity
import kotlinx.android.synthetic.main.activity_perfil_usuario.view.tvEmail
import kotlinx.android.synthetic.main.activity_perfil_usuario.view.tvNombres
import kotlinx.android.synthetic.main.activity_perfil_usuario.view.tvNumero

class EditarPerfilActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditarPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues() {
        setProfile()

        binding.btnCambiarContrasena.setOnClickListener {
            startActivity(Intent(this,CambiarContrasenaActivity::class.java))
        }
        var id = intent.getIntExtra("id", 0)
        println("id $id")
    }

    private fun setProfile() {
//        val usuario = SharedPreferences.getPrefUsuario(requireContext())!!
//        "${usuario.nom_usuario} ${usuario.ape_usuario}".also { view.tvNombres.text = it }
//        view.tvEmail.text = usuario.email_usuario
//        view.tvNumero.text = usuario.cel_usuario
    }
}