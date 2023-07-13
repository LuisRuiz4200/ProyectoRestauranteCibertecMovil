package com.example.restaurante.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class ColaboradorBD (
    @PrimaryKey (autoGenerate = true)
    var id_colaborador : Int = 0,
    var id_tipo_colaborador : Int =0,
    var nom_colaborador : String = "",
    var ape_colaborador : String = "",
    var dni_colaborador : String = "",
    var imagen_colaborador : Byte = 0,
    var fechaReg_colaborador : Date? = null,
    var fechcaAct_colaborador : Date? = null,
    var estado_colaborador : String = "",
)