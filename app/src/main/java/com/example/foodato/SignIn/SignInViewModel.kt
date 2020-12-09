package com.example.foodato.SignIn

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodato.FoodatoDatabase.LoginData
import com.example.foodato.FoodatoDatabase.LoginDataDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel(val dataSource: LoginDataDao, application: Application) : ViewModel() {

    var password=MutableLiveData<String>()
    val _password:LiveData<String>
    get() = password
    fun getCurrentUserData(userEmail: String, userPassword: String) {
        viewModelScope.launch {
//           var currentUserData= dataSource.get(userEmail)
//            password.value=currentUserData?.password
           var currentUserData= getData(userEmail)
            password.value=currentUserData?.password
        }
    }

    suspend fun getData(email: String):LoginData {
        //retrieving data on background thread
        return withContext(Dispatchers.IO) {
             dataSource.get(email)!!
        }
    }
}