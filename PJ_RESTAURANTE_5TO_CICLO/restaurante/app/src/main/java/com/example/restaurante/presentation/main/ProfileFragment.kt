package com.example.restaurante.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.restaurante.R
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.data.room.BDPolleria
import com.example.restaurante.domain.viewmodel.UsuarioViewModel
import com.example.restaurante.presentation.perfil.EditarPerfil.EditarPerfilActivity
import com.example.restaurante.presentation.perfil.MisDirecciones.MisDireccionesActivity
import com.example.restaurante.presentation.perfil.MisPedidos.MisPedidosActivity
import com.example.restaurante.presentation.perfil.MisTarjetas.MisTarjetasActivity
import com.example.restaurante.presentation.registro.RegistroActivity
import kotlinx.android.synthetic.main.activity_perfil_usuario.view.btnDirecciones
import kotlinx.android.synthetic.main.activity_perfil_usuario.view.btnEditar
import kotlinx.android.synthetic.main.activity_perfil_usuario.view.btnFavoritos
import kotlinx.android.synthetic.main.activity_perfil_usuario.view.btnPedidos
import kotlinx.android.synthetic.main.activity_perfil_usuario.view.btnTarjetas
import kotlinx.android.synthetic.main.activity_perfil_usuario.view.tvEmail
import kotlinx.android.synthetic.main.activity_perfil_usuario.view.tvNombres
import kotlinx.android.synthetic.main.activity_perfil_usuario.view.tvNumero

class ProfileFragment : Fragment() {
    private lateinit var viewModel: UsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_perfil_usuario,container,false)
        initValues(view)
        initObservers(view)
        return view
    }

    private fun initValues(view: View) {
        viewModel = ViewModelProvider(this)[UsuarioViewModel::class.java]
        setProfile(view)

        view.btnEditar.setOnClickListener {
            startActivity(Intent(requireContext(), EditarPerfilActivity::class.java))
        }
        view.btnPedidos.setOnClickListener {
            startActivity(Intent(requireContext(), MisPedidosActivity::class.java))
        }
        view.btnDirecciones.setOnClickListener {
            startActivity(Intent(requireContext(), MisDireccionesActivity::class.java))
        }
        view.btnFavoritos.setOnClickListener {
            // Temporal
            startActivity(Intent(requireContext(), RegistroActivity::class.java))
        }
        view.btnTarjetas.setOnClickListener {
            startActivity(Intent(requireContext(), MisTarjetasActivity::class.java))
        }
    }

    private fun initObservers(view: View) {

    }

    private fun setProfile(view: View) {
        val usuario = SharedPreferences.getPrefUsuario(requireContext())!!
        "${usuario.nom_usuario} ${usuario.ape_usuario}".also { view.tvNombres.text = it }
        view.tvEmail.text = usuario.email_usuario
        view.tvNumero.text = usuario.cel_usuario
    }
}
