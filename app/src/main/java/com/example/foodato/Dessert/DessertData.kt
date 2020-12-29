package com.example.foodato.Dessert

class DessertData  {  private var mImageResourceId: Int = 0
    private var mPrice: Int = 0
    private var mDessertName: String? = null

    constructor(ImageResourceId: Int, price: Int, DessertName: String) {
        mImageResourceId = ImageResourceId
        mPrice = price
        mDessertName = DessertName
    }

    constructor()

    fun getImaageResourceId(): Int {
        return mImageResourceId
    }

    fun getPrice(): String {
        return "₹$mPrice"
    }

    fun getDessertName(): String {
        return mDessertName.toString()

    }
}