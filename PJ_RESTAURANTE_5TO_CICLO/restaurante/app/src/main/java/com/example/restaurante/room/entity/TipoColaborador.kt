package com.example.restaurante.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TipoColaborador(
    @PrimaryKey(autoGenerate = true)
    var id_tipo_colaborador:Int = 0,
    var des_tipo_colaborador:String = ""
)
