package com.example.restaurante.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.restaurante.R
import com.example.restaurante.room.dao.ProductoDao
import com.example.restaurante.room.entity.ProductoBD

abstract class BDPolleria {
    @Database(
        entities = [
            ProductoBD::class
        ],
        version = 1,
        exportSchema= false
    )
    abstract class BDPolleria: RoomDatabase() {
        abstract fun productoDao() : ProductoDao

        companion object{
            private var instancia: BDPolleria? = null
            @Synchronized
            fun getInstancia(context: Context):BDPolleria{
                if(instancia == null){
                    instancia == Room.databaseBuilder(
                        context, BDPolleria::class.java,
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
}