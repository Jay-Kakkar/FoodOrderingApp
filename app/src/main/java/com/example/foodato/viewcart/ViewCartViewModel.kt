package com.example.foodato.viewcart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class ViewCartViewModel(data: ArrayList<CartData>) : ViewModel() {
    var arrayData = MutableLiveData<ArrayList<CartData>>()
    val _arrayData: LiveData<ArrayList<CartData>>
        get() = arrayData

    var list = Transformations.map(_arrayData) {
    Log.e(this.toString(),"print name ${it.forEach { it.getDishName() }}")
    }


}