package com.example.foodato.viewcart

class CartData {
    private var mPrice: Int = 0
    private var mDishName: String? = null

    constructor( price: Int, DishName: String) {
        mPrice = price
        mDishName = DishName
    }

    constructor()


    fun getPrice(): String {
        return "₹$mPrice"
    }

    fun getPriceInt(): Int
    {
        return mPrice
    }
    fun getDishName(): String {
        return mDishName.toString()

    }
}
