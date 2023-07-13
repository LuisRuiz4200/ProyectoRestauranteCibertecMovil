package com.example.restaurante.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class ComentarioBD(
    @PrimaryKey(autoGenerate = true)
    var id_comentario: Int = 0,
    var id_usuario_cliente: Int = 0,
    var des_comentario: String = "",
    var cantEstrella_comentario: String = "",
    var fechaReg_comentario: Date? = null,
    var estado_comentario:String = ""
)
