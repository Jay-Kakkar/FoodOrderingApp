package com.example.foodato.SignIn

import android.app.Application
import android.util.Log
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

    var password = MutableLiveData<String>()
    val _password: LiveData<String>
        get() = password

    var usernamePref=MutableLiveData<String>()
    val _usernamePref:LiveData<String>
    get() = usernamePref
    fun getCurrentUserData(userEmail: String) {
        viewModelScope.launch {
//           var currentUserData= dataSource.get(userEmail)
//            password.value=currentUserData?.password
            Log.e(this.toString(),"3333333333333333333${getData(userEmail)}")

            val currentUserData = getData(userEmail)
            password.value = currentUserData?.password
            usernamePref.value=currentUserData?.userName
            Log.e(this.toString(),"33335555555555555555${password.value}")

            Log.e(this.toString(),"33335555555555555555${usernamePref
                .value}")

        }

    }

    suspend fun getData(email: String): LoginData {
        //retrieving data on background thread
        return withContext(Dispatchers.IO) {
            Log.e(this.toString(),"3333333333333333333${dataSource.get(email)}")

            dataSource.get(email)

        }
    }

//    fun update(status: Boolean, email: String) {
//        viewModelScope.launch {
//            updateLoginStatus(status, email)
//        }
//    }
//
//    private suspend fun updateLoginStatus(status: Boolean, email: String) {
//        return withContext(Dispatchers.IO) {
//            dataSource.update(status, email)
//        }
//    }
}