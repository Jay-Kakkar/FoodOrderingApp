package com.example.foodato.Starters

class StartersData {
    private var mImageResourceId: Int = 0
    private var mPrice: Int = 0
    private var mStarterName: String? = null

    constructor(ImageResourceId: Int, price: Int, StarterName: String) {
        mImageResourceId = ImageResourceId
        mPrice = price
        mStarterName = StarterName
    }

    constructor()

    fun getImaageResourceId(): Int {
        return mImageResourceId
    }

    fun getPrice(): String {
        return "₹$mPrice"
    }

    fun getPriceInt(): Int
    {
        return mPrice
    }
    fun getStarterName(): String {
        return mStarterName.toString()

    }
}