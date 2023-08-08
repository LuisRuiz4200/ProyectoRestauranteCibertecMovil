package com.example.restaurante.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.restaurante.R
import com.example.restaurante.data.room.BDPolleria

class MisFavoritosFragment : Fragment(){
    private lateinit var database : BDPolleria

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_mis_favoritos,container,false)
        initValues(view)
        initObservers(view)
        return view
    }

    private fun initValues(view: View) {
//        GENERAR LISTA DE FAVORITOS TODO
    }

    private fun initObservers(view: View) {
    }
}