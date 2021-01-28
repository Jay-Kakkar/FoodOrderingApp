package com.example.foodato.Dessert

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.foodato.viewcart.CartData

import com.example.foodato.R
import com.example.foodato.databinding.FragmentDessertBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Suppress("DEPRECATION")
class Dessert :Fragment() {
    private lateinit var binding: FragmentDessertBinding
    private var data = ArrayList<DessertData>()
    private lateinit var adapter: DessertAdapter
    var totalPrice:Int=0
    var prevPrice=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_dessert,container,false)

        var details = prefData()
        if (details.isNotEmpty()) {
            details.forEach {
                prevPrice += it.getPriceInt()
                var dish=it.getDishName()

                Log.e(this.toString(), "********************$prevPrice")
                Log.e(this.toString(), "********************${dish}")

            }
            totalPrice = prevPrice

            binding.total.visibility = View.VISIBLE
            binding.symbol.visibility = View.VISIBLE
            binding.total.text = totalPrice.toString()

        }

        adapter = DessertAdapter(data, DessertAdapter.Clicklisteners{ price: Int, dessert: String ->
            totalPrice = Integer.parseInt(binding.total.text.toString()) + price
            if (totalPrice > 0) {
                binding.total.visibility = View.VISIBLE
                binding.symbol.visibility = View.VISIBLE
            }
            details.add(CartData(price, dessert))

            val appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context)
            val prefsEditor = appSharedPrefs.edit()

            val gson = Gson()
            val json: String = gson.toJson(details)
            prefsEditor.putString("Details", json)
            prefsEditor.apply()
            Log.e(this.toString(), "((((((((((((${json}")


            binding.total.text = totalPrice.toString()
        })
        binding.recycler.adapter=adapter
        binding.viewcart.setOnClickListener {
            findNavController().navigate(DessertDirections.actionDessert2ToViewCart())
        }
        return binding.root
    }



    override fun onStart() {
        super.onStart()
        if(data.size==0){
            initialiseDesserts()
        }
    }

    private fun initialiseDesserts(): ArrayList<DessertData> {
        data.add(DessertData(R.drawable.berrycreamcustard,200,"Berry Cream Custard"))
        data.add(DessertData(R.drawable.brownie,170,"Chocolate Brownie"))
        data.add(DessertData(R.drawable.chocolatemouse,160,"Chocolate Mouse"))
        data.add(DessertData(R.drawable.cupcake,200,"Cup Cake"))
        data.add(DessertData(R.drawable.frenchmacaroons,220,"Macarons"))
        data.add(DessertData(R.drawable.icecream,190,"Ice Cream"))
        data.add(DessertData(R.drawable.donuts,150,"Donuts"))
        data.add(DessertData(R.drawable.cheesecake,200,"Cheese Cake"))
        return data
    }
    fun prefData(): ArrayList<CartData> {

        val appSharedPrefsget = PreferenceManager
            .getDefaultSharedPreferences(context)
        val detail: ArrayList<CartData>
        val gsonG = Gson()
        val jsonG = appSharedPrefsget.getString("Details", "")

        detail = if (jsonG?.isEmpty() == true) {
            Log.e(this.toString(), "((((((((((((&&&&&&&&&&&&/DEmpty")

            ArrayList()
        } else {
            val type = object : TypeToken<ArrayList<CartData>>() {}.type
            gsonG.fromJson(jsonG, type)
        }
//        val type = object : TypeToken<ArrayList<CartData>>() {}.type
        return detail
//        return gsonG.fromJson(jsonG, type)

    }
}