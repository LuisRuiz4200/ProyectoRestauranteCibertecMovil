package com.example.restaurante.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    var id_usuario:Int = 0,
    var id_tipoUsuario:Int = 0,
    var nom_usuario: String = "",
    var ape_usuario: String = "",
    var tel_usuario: String = "",
    var cel_usuario: Int = 0,
    var id_distrito:Int = 0,
    var dir_usuario: String = "",
    var dni_usuario: String = "",
    var email_usuario: String = "",
    var password_usuario : String = "",
    var imagen_usuario : String = "",

    var estado_usuario: String = ""
)


//var fechaReg_usuario: Date? = null,
//var fechaAct_usuario: Date? = null,