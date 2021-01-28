package com.example.foodato.viewcart

class ViewCartData {
    private lateinit var mDishName:String
    private  var mDishPrice:Int=0
    constructor(dishName:String,dishPrice:Int){
        mDishName=dishName
        mDishPrice=dishPrice
    }
    fun getDishName():String{
        return mDishName
    }
    fun getDishPrice():Int{
        return mDishPrice
    }
}