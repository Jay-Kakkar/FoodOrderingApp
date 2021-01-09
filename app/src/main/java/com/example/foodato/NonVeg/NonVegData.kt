package com.example.foodato.NonVeg

class NonVegData {  private var mImageResourceId: Int = 0
    private var mPrice: Int = 0
    private var mNonVegFoodName: String? = null

    constructor(ImageResourceId: Int, price: Int, NonVegFoodName: String) {
        mImageResourceId = ImageResourceId
        mPrice = price
        mNonVegFoodName = NonVegFoodName
    }

    constructor()

    fun getImaageResourceId(): Int {
        return mImageResourceId
    }

    fun getPrice(): String {
        return "₹$mPrice"
    }
    fun getPriceInt(): Int {
        return mPrice
    }

    fun getNonVegFoodName(): String {
        return mNonVegFoodName.toString()

    }
}