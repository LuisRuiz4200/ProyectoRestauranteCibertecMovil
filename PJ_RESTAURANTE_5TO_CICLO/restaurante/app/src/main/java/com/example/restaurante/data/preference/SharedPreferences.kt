package com.example.restaurante.data.preference

import android.content.Context
import com.example.restaurante.data.room.entity.Usuario
//import com.example.restaurante.domain.entity.Usuario
import com.google.gson.Gson

object SharedPreferences {
    val PREFERENCE_NAME="SharedPreferences"
    val CLASS_NAME="Usuario"

    fun setPrefUsuario(context : Context,usuario: Usuario) : Int{
        val prefere=context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)
        val editor=prefere.edit()
        val gson=  Gson()
        val usuarioJson=gson.toJson(usuario)
        editor.putString(CLASS_NAME,usuarioJson)
        editor.apply()
        return 1
    }

    fun getPrefUsuario(context : Context) :Usuario?{
        var usuario : Usuario? = null
        val prefe=context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)
        if(prefe.contains(CLASS_NAME)){
            val usuarioJsonDese=prefe.getString(CLASS_NAME,"")
            val gson= Gson()
            usuario=gson.fromJson(usuarioJsonDese,Usuario::class.java)
        }
        return usuario
    }

    fun deletePrefUsuario(context: Context){
        val pref = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.clear()
        editor.apply()
    }
}