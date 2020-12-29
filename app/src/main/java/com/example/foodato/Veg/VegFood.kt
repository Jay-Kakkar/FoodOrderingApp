package com.example.foodato.Veg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.foodato.R
import com.example.foodato.Starters.StartersAdapter
import com.example.foodato.Starters.StartersData
import com.example.foodato.databinding.FragmentStartersBinding
import com.example.foodato.databinding.FragmentVegFoodBinding


class VegFood : Fragment() {
    private lateinit var binding: FragmentVegFoodBinding
    private var data = ArrayList<vegFoodData>()
    private lateinit var adapter: VegFoodAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_veg_food,container,false)
        adapter= VegFoodAdapter(data)

        binding.recycler.adapter=adapter

        return binding.root
    }



    override fun onStart() {
        super.onStart()
        if(data.size==0){
            initialiseVegFood()
        }
    }

    private fun initialiseVegFood(): ArrayList<vegFoodData> {
        data.add(vegFoodData(R.drawable.chaap,150,"Malai Chaap"))
        data.add(vegFoodData(R.drawable.cholebatura,140,"Chole Bhature"))
        data.add(vegFoodData(R.drawable.dalmakhni,130,"Dal Makhni"))
        data.add(vegFoodData(R.drawable.dosa,160,"Masala Dosa"))
        data.add(vegFoodData(R.drawable.idli,150,"Sambar Idli"))
        data.add(vegFoodData(R.drawable.malaikofta,120,"Malai Kofta"))
        data.add(vegFoodData(R.drawable.palakpaneer,130,"Palak Paneer"))
        data.add(vegFoodData(R.drawable.shahipaneer,200,"Shahi Paneer"))
        return data
    }
}