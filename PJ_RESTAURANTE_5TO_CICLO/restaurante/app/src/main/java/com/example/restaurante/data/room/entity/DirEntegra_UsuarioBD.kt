package com.example.restaurante.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class DirEntegra_UsuarioBD(
    @PrimaryKey
    var id_usuario:Int = 0,
    @PrimaryKey(autoGenerate = true)
    var id_direntrega:Int = 0,
    var id_distrito:Int = 0,
    var des_direntrega:String = "",
    var fechaReg_direntrega:Date?=null,
    var estado_direntrega:String = ""
)
