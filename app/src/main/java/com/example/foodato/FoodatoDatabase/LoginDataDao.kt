package com.example.foodato.FoodatoDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LoginDataDao {
    @Insert
    suspend fun insert(loginData: LoginData)

    @Query("SELECT * FROM LoginData_table WHERE Email=:key")
    suspend fun get(key: Long): LoginData?

}