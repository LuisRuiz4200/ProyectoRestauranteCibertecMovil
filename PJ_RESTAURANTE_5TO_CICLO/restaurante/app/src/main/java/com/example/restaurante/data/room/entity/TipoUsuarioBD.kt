package com.example.restaurante.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TipoUsuarioBD(
    @PrimaryKey(autoGenerate = true)
    var id_tipo_usuario:Int =0,
    var des_tipo_usuario:String = ""
)
