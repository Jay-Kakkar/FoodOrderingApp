package com.example.foodato.Veg

class vegFoodData {
    private var mImageResourceId: Int = 0
    private var mPrice: Int = 0
    private var mVegFoodName: String? = null

    constructor(ImageResourceId: Int, price: Int, VegFoodName: String) {
        mImageResourceId = ImageResourceId
        mPrice = price
        mVegFoodName = VegFoodName
    }

    constructor()

    fun getImaageResourceId(): Int {
        return mImageResourceId
    }

    fun getPrice(): String {
        return "₹$mPrice"
    }

    fun getVegFoodName(): String {
        return mVegFoodName.toString()

    }
}