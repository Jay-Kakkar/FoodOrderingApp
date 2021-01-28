package com.example.foodato.NonVeg

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

import com.example.foodato.databinding.FragmentNonVegFoodBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Suppress("DEPRECATION")
class NonVegFood : Fragment() {
    private lateinit var binding: FragmentNonVegFoodBinding
    private var data = ArrayList<NonVegData>()
    private lateinit var adapter: NonVegAdapter
    var totalPrice:Int=0
    var prevPrice=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_non_veg_food,container,false)

        var details = prefData()
        if (details.isNotEmpty()) {
            details.forEach {
                prevPrice += it.getPriceInt()
                val dish=it.getDishName()

                Log.e(this.toString(), "********************$prevPrice")
                Log.e(this.toString(), "********************${dish}")

            }
            totalPrice = prevPrice

            binding.total.visibility = View.VISIBLE
            binding.symbol.visibility = View.VISIBLE
            binding.total.text = totalPrice.toString()

        }

        adapter = NonVegAdapter(data,NonVegAdapter.Clicklisteners{ price: Int, nameNonVeg: String ->
            totalPrice = Integer.parseInt(binding.total.text.toString()) + price
            if (totalPrice > 0) {
                binding.total.visibility = View.VISIBLE
                binding.symbol.visibility = View.VISIBLE
            }
            details.add(CartData(price, nameNonVeg))

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
            findNavController().navigate(NonVegFoodDirections.actionNonVegFoodToViewCart())
        }
        return binding.root
    }



    override fun onStart() {
        super.onStart()
        if(data.size==0){
            initialiseNonVegFood()
        }
    }

    private fun initialiseNonVegFood(): ArrayList<NonVegData> {
        data.add(NonVegData(R.drawable.butterchicken,350,"Butter Chicken"))
        data.add(NonVegData(R.drawable.chickenbiryani,370,"Chicken Biryani"))
        data.add(NonVegData(R.drawable.chickenfries,400,"Chicken Fries"))
        data.add(NonVegData(R.drawable.chickenroll,320,"Chicken Roll"))
        data.add(NonVegData(R.drawable.eggtoast,330,"Egg Toast"))
        data.add(NonVegData(R.drawable.fishtikka,400,"Fish Tikka"))
        data.add(NonVegData(R.drawable.prawns,350,"Prawns"))
        data.add(NonVegData(R.drawable.tandoorichicken,380,"Tandoori Chicken"))
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