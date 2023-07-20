package com.example.restaurante.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MedioPagoBD(
    @PrimaryKey(autoGenerate = true)
    var id_medioPago:Int=0,
    var des_medioPago:String=""
)
