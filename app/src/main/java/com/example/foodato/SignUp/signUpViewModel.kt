package com.example.foodato.SignUp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodato.FoodatoDatabase.LoginData
import com.example.foodato.FoodatoDatabase.LoginDataDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel(val dataSource: LoginDataDao,application: Application) : ViewModel() {
    fun insertData(
        name: String,
        username: String,
        email: String,
        password: String,
        newUserData: LoginData
    ) {
        viewModelScope.launch {

            newUserData.name = name
            newUserData.userName = username
            newUserData.email = email
            newUserData.password = password
            insert(newUserData)
        }
    }
    private suspend fun insert(newUserData: LoginData) {
        withContext(Dispatchers.IO) {
            dataSource.insert(newUserData)
        }
    }
}