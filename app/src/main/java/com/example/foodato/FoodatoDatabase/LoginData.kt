package com.example.foodato.FoodatoDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LoginData_table")
data class LoginData (

    @PrimaryKey(autoGenerate = true)
    var userNumber: Long = 0L,
    @ColumnInfo(name = "Name")
    var name: String? = null,
    @ColumnInfo(name = "Username")
    var userName: String? = null,
    @ColumnInfo(name = "Password")
    var password: String? = null,
    @ColumnInfo(name = "Email")
    var email:String?=null,
)