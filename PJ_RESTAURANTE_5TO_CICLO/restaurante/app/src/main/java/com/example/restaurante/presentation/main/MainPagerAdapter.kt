package com.example.restaurante.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainPagerAdapter (fa : FragmentActivity) : FragmentStateAdapter(fa){
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {ListProductosFragment()}
            1 -> {ListProductosFragment()}
            2 -> {CartFragment()}
            3 -> {ProfileFragment()}
            else -> {ListProductosFragment()}
        }
    }


}