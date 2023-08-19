package com.example.restaurante.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurante.R
import com.example.restaurante.data.preference.SharedPreferences
import com.example.restaurante.data.room.entity.Favorito
import com.example.restaurante.data.room.entity.Usuario
import com.example.restaurante.domain.viewmodel.FavoritoViewModel
import kotlinx.android.synthetic.main.fragment_favorito.view.rvFavorito

class FavoritoFragment : Fragment(), FavoritoAdapter.ICard{
    private lateinit var viewModelFavorito: FavoritoViewModel
    private lateinit var favoritoAdapter: FavoritoAdapter
    private var lstProducto : MutableList<Favorito> = ArrayList()
    private var usuario = Usuario()

    override fun onCreateView
        (inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?)
        : View {

        val view = inflater.inflate(R.layout.fragment_favorito,container,false)
        initValues(view)
        initObservers(view)
        return view
    }

    override fun onResume() {
        super.onResume()
        viewModelFavorito.getFavorito(usuario.id_usuario)
    }

    private fun initValues(view: View) {
        viewModelFavorito = ViewModelProvider(this)[FavoritoViewModel::class.java]
        usuario = SharedPreferences.getPrefUsuario(requireContext())!!
        favoritoAdapter = FavoritoAdapter(lstProducto, this, viewModelFavorito)
        view.rvFavorito.layoutManager = LinearLayoutManager(requireContext())
        view.rvFavorito.adapter = favoritoAdapter
        viewModelFavorito.getFavorito(usuario.id_usuario)
    }

    private fun initObservers(view: View) {
        viewModelFavorito.getFavorito.observe(viewLifecycleOwner){
            favoritoAdapter.update(it)
        }
        viewModelFavorito.saveFavorito.observe(viewLifecycleOwner){
            viewModelFavorito.getFavorito(usuario.id_usuario)
        }
        viewModelFavorito.deleteFavorito.observe(viewLifecycleOwner){
            viewModelFavorito.getFavorito(usuario.id_usuario)
        }
    }

    override fun onCardClick(item: Favorito) {
//        TODO("Not yet implemented")
    }
}