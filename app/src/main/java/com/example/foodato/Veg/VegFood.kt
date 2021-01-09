package com.example.foodato.Veg

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodato.CartData
import com.example.foodato.R
import com.example.foodato.databinding.FragmentVegFoodBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Suppress("DEPRECATION", "DEPRECATION")
class VegFood : Fragment() {
    private lateinit var binding: FragmentVegFoodBinding
    private var data = ArrayList<vegFoodData>()
    private lateinit var adapter: VegFoodAdapter
    var totalPrice: Int = 0
    var prevPrice:Int=0
    private var cartData=ArrayList<CartData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_veg_food, container, false)
        var details = prefData()
        if (details.isNotEmpty()) {
            details.forEach {
                prevPrice += it.getPriceInt()
                var dish=it.getDishName()
                Log.e(this.toString(),"********************$prevPrice")
                Log.e(this.toString(), "********************${dish}")

            }
            totalPrice=prevPrice

            binding.total.visibility = View.VISIBLE
            binding.symbol.visibility = View.VISIBLE
            binding.total.text = totalPrice.toString()

        }
        adapter = VegFoodAdapter(
            data,
            VegFoodAdapter.Clicklisteners { price: Int, nameVeg: String ->


                totalPrice = Integer.parseInt(binding.total.text.toString()) + price
                if (totalPrice > 0) {
                    binding.total.visibility = View.VISIBLE
                    binding.symbol.visibility = View.VISIBLE
                }
                details.add(CartData(price, nameVeg))

                val appSharedPrefs = PreferenceManager
                    .getDefaultSharedPreferences(context)
                val prefsEditor = appSharedPrefs.edit()

                val gson = Gson()
                val json: String = gson.toJson(details)
                prefsEditor.putString("Details", json)
                prefsEditor.apply()
                Log.e(this.toString(),"((((((((((((${json}")


                binding.total.text = totalPrice.toString()
            })
        binding.recycler.adapter = adapter
        binding.viewcart.setOnClickListener {
            findNavController().navigate(VegFoodDirections.actionVegFoodToViewCart())
        }
        return binding.root
    }


    override fun onStart() {
        super.onStart()
        if (data.size == 0) {
            initialiseVegFood()
        }
    }

    private fun initialiseVegFood(): ArrayList<vegFoodData> {
        data.add(vegFoodData(R.drawable.chaap, 150, "Malai Chaap"))
        data.add(vegFoodData(R.drawable.cholebatura, 140, "Chole Bhature"))
        data.add(vegFoodData(R.drawable.dalmakhni, 130, "Dal Makhni"))
        data.add(vegFoodData(R.drawable.dosa, 160, "Masala Dosa"))
        data.add(vegFoodData(R.drawable.idli, 150, "Sambar Idli"))
        data.add(vegFoodData(R.drawable.malaikofta, 120, "Malai Kofta"))
        data.add(vegFoodData(R.drawable.palakpaneer, 130, "Palak Paneer"))
        data.add(vegFoodData(R.drawable.shahipaneer, 200, "Shahi Paneer"))
        return data
    }

    fun prefData(): ArrayList<CartData> {

        val appSharedPrefsget = PreferenceManager
            .getDefaultSharedPreferences(context)
        val detail: ArrayList<CartData>
        val gsonG = Gson()
        val jsonG = appSharedPrefsget.getString("Details", "")
//                val mStudentObject: CartData = gsonG.fromJson(jsonG, CartData::class.java)

        detail = if (jsonG?.isEmpty() == true) {
            ArrayList<CartData>()
        } else {
            val type = object : TypeToken<ArrayList<CartData>>() {}.type
            gsonG.fromJson(jsonG, type)
        }
//        val type = object : TypeToken<ArrayList<CartData>>() {}.type
        return detail
//        return gsonG.fromJson(jsonG, type)

    }
}