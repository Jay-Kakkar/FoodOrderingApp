package com.example.foodato.FoodatoDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [LoginData::class], version = 1, exportSchema = false)

abstract class FoodatoDatabaseBuild: RoomDatabase() {
    abstract val loginDataDao:LoginDataDao
    companion object{
        //volatile means value is not cached and changes will be used by all
        @Volatile
        private var INSTANCE: FoodatoDatabaseBuild? =null
        fun getInstance(context: Context): FoodatoDatabaseBuild {
            synchronized(this){
                var instance= INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FoodatoDatabaseBuild::class.java,
                        "foodato_database_history"
                    )

                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}