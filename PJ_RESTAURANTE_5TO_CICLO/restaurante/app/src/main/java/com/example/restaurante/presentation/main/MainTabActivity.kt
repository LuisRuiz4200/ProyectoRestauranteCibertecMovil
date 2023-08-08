package com.example.restaurante.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.restaurante.R
import com.example.restaurante.databinding.ActivityMainTabBinding
import com.example.restaurante.domain.viewmodel.CartViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MainTabActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainTabBinding
    private lateinit var cartViewModel: CartViewModel
    private val adapter by lazy {MainPagerAdapter(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTabBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        initValues()
    }

    private fun initValues() {
        //Evitar el desplazamiento entre fragments por desplazamiento
        binding.pager.isUserInputEnabled = false
        binding.pager.adapter = adapter
        val tabLayoutMediator = TabLayoutMediator(binding.tabLayout, binding.pager) {
            tab, position -> when (position){
                0 -> { tab.setIcon(R.drawable.ic_home_gray)}
                1 -> { tab.setIcon(R.drawable.ic_favorites_gray)}
                2 -> { tab.setIcon(R.drawable.ic_cart_gray)}
                3 -> { tab.setIcon(R.drawable.ic_user_profile)}
            }
        }
        tabLayoutMediator.attach()
    }
}