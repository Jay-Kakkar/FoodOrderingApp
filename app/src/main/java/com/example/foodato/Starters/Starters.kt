package com.example.foodato.Starters


import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodato.viewcart.CartData
import com.example.foodato.R
import com.example.foodato.databinding.FragmentStartersBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Suppress("DEPRECATION")
class Starters : Fragment() {
    private lateinit var binding: FragmentStartersBinding
    private var data = ArrayList<StartersData>()
    private lateinit var adapter: StartersAdapter
    var totalPrice: Int = 0
    var prevPrice = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_starters, container, false)

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

        adapter = StartersAdapter(
            data,
            StartersAdapter.Clicklisteners { price: Int, nameStarter: String ->
                totalPrice = Integer.parseInt(binding.total.text.toString()) + price
                if (totalPrice > 0) {
                    binding.total.visibility = View.VISIBLE
                    binding.symbol.visibility = View.VISIBLE
                }
                details.add(CartData(price, nameStarter))

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
        binding.viewcart.setOnClickListener {
            findNavController().navigate(StartersDirections.actionStartersToViewCart())
        }
        binding.recycler.adapter = adapter
//        binding.total.setOnClickListener {
//            var total=binding.
//        }
        return binding.root
    }


    override fun onStart() {
        super.onStart()
        if (data.size == 0) {
            initialiseStarters()
        }
    }

    private fun initialiseStarters(): ArrayList<StartersData> {
        data.add(StartersData(R.drawable.paneertikka, 150, "Paneer Tikka"))
        data.add(StartersData(R.drawable.chillypotato, 140, "Chilly Potato"))
        data.add(StartersData(R.drawable.springroll, 130, "Spring Roll"))
        data.add(StartersData(R.drawable.whitesauspasta, 160, "White Saus Pasta"))
        data.add(StartersData(R.drawable.redssauspasta, 150, "Red Saus Pasta"))
        data.add(StartersData(R.drawable.chowmein, 120, "Chowmein"))
        data.add(StartersData(R.drawable.momos, 130, "Momos"))
        data.add(StartersData(R.drawable.pizza, 200, "Pizza"))
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