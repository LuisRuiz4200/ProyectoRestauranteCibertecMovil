package com.example.restaurante.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.restaurante.R
import com.example.restaurante.data.room.dao.CartDao
import com.example.restaurante.data.room.dao.PedidoDao
import com.example.restaurante.data.room.dao.ProductoDao
import com.example.restaurante.data.room.dao.TarjetaDao
import com.example.restaurante.data.room.dao.UsuarioDao
import com.example.restaurante.data.room.entity.Cart
import com.example.restaurante.data.room.entity.Distrito
import com.example.restaurante.data.room.entity.Pedido
import com.example.restaurante.data.room.entity.Producto
import com.example.restaurante.data.room.entity.Tarjeta
import com.example.restaurante.data.room.entity.Usuario

@Database(
    entities = [
        Producto::class,
        Usuario::class,
        Cart::class,
        Pedido::class,
        Tarjeta::class,
        Distrito::class,
    ],
    version = 19,
    exportSchema = false
)
abstract class BDPolleria : RoomDatabase()  {
    abstract fun productoDao() : ProductoDao
    abstract fun usuarioDao() : UsuarioDao
    abstract fun cartDao() : CartDao
    abstract fun pedidoDao(): PedidoDao
    abstract fun tarjetaDao(): TarjetaDao
    companion object{
        private var instancia : BDPolleria? =null
        @Synchronized
        fun getInstancia(context:Context) : BDPolleria {
            if (instancia == null){
                instancia= Room.databaseBuilder(
                    context,BDPolleria::class.java,
                    context.getString(R.string.database_name)
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instancia as BDPolleria
        }
    }
}