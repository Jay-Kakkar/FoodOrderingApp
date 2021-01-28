package com.example.foodato.viewcart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewCartModelFactory(
    private val data: ArrayList<CartData>) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewCartViewModel::class.java)) {
            return ViewCartViewModel(data) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
