package com.example.restaurante.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.restaurante.R
import com.example.restaurante.room.dao.ProductoDao
import com.example.restaurante.room.entity.ProductoBD

@Database(
    entities = [
        ProductoBD::class
    ],
    version = 1,
    exportSchema= false
)
abstract class RestauranteBD:RoomDatabase() {
    abstract fun productoDao() : ProductoDao

    companion object{
        private var instancia: RestauranteBD? = null
        @Synchronized
        fun getInstancia(context:Context):RestauranteBD{
            if(instancia == null){
                instancia == Room.databaseBuilder(
                    context, RestauranteBD::class.java,
                    context.getString(R.string.database_name)
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }

            return instancia as RestauranteBD
        }
    }

}