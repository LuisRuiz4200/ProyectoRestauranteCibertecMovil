package com.example.restaurante.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ColaboradorBD (
    @PrimaryKey (autoGenerate = true)
    var id_colaborador : Int = 0,
    var id_tipo_colaborador : Int =0,
    var nom_colaborador : String = "",
    var ape_colaborador : String = "",
    var dni_colaborador : String = "",
    var imagen_colabordar : Byte = 0


)